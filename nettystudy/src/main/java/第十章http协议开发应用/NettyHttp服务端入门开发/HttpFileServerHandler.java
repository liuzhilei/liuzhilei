package 第十章http协议开发应用.NettyHttp服务端入门开发;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.LOCATION;
import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.METHOD_NOT_ALLOWED;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressiveFutureListener;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;

/**
 * @author lilinfeng
 * @version 1.0
 * @date 2014年2月14日
 */
public class HttpFileServerHandler extends
        SimpleChannelInboundHandler<FullHttpRequest> {
    private final String url;

    public HttpFileServerHandler(String url) {
        this.url = url;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx,
                                FullHttpRequest request) throws Exception {
        if (!request.getDecoderResult().isSuccess()) {//对http请求消息的解码结果进行判断
            sendError(ctx, BAD_REQUEST);
            return;
        }
        if (request.getMethod() != GET) {//对请求行中的方法进行判断
            sendError(ctx, METHOD_NOT_ALLOWED);
            return;
        }
        final String uri = request.getUri();
        final String path = sanitizeUri(uri);//对请求url进行包装
        if (path == null) {//如果构造的uri不合法，返回403错误
            sendError(ctx, FORBIDDEN);
            return;
        }
        File file = new File(path);//使用新组装的uri路径构造file
        if (file.isHidden() || !file.exists()) {//如果文件不存在或者是系统隐藏文件，构造404异常返回
            sendError(ctx, NOT_FOUND);
            return;
        }
        if (file.isDirectory()) {//如果文件是目录，就发送目录的链接到客户端浏览器
            if (uri.endsWith("/")) {
                sendListing(ctx, file);
            } else {
                sendRedirect(ctx, uri + '/');
            }
            return;
        }
        if (!file.isFile()) {//如果在浏览器点击超链接直接打开或者下载文件，会执行这个判断，对链接的文件进行合法性判断
            sendError(ctx, FORBIDDEN);
            return;
        }
        RandomAccessFile randomAccessFile = null;//校验通过，使用随机文件读写类以只读的方式打开文件
        try {
            randomAccessFile = new RandomAccessFile(file, "r");// 以只读的方式打开文件
        } catch (FileNotFoundException fnfe) {
            sendError(ctx, NOT_FOUND);
            return;
        }
        long fileLength = randomAccessFile.length();//获取文件的长度
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);//构造成功的http应答消息
        setContentLength(response, fileLength);//消息头中设置contentLength
        setContentTypeHeader(response, file);//消息头中设置contentType
        if (isKeepAlive(request)) {//判断是否是Keep-Alive,如果是，则在应答消息头中设置connection为keep-alive
            response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
        ctx.write(response);//发送响应消息
        ChannelFuture sendFileFuture;
        sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0,
                fileLength, 8192), ctx.newProgressivePromise());//通过netty的chunkedFile对象直接将文件写入到发送缓冲区中
        sendFileFuture.addListener(new ChannelProgressiveFutureListener() {//最后为sendFileFuture增加GenericFutureListener,如果发送完成，打印"Transfer complete"
            @Override
            public void operationProgressed(ChannelProgressiveFuture future,
                                            long progress, long total) {
                if (total < 0) { // total unknown
                    System.err.println("Transfer progress: " + progress);
                } else {
                    System.err.println("Transfer progress: " + progress + " / "
                            + total);
                }
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future)
                    throws Exception {
                System.out.println("Transfer complete.");
            }
        });
        ChannelFuture lastContentFuture = ctx
                .writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);//如果使用chunked编码，最后需要发送一个编码结束的空消息提，将lastHttpContent的EMPTY_LAST_CONTENT发送到缓冲区，标识所有消息体已经发送完成，同时调用flush方法将之前的发送缓冲区的消息刷新到socketChannel中发送给对方
        if (!isKeepAlive(request)) {//如果是非keep-Alive，最后一包消息发送完成之后，服务端要主动关闭
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }

    private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");

    private String sanitizeUri(String uri) {
        try {
            uri = URLDecoder.decode(uri, "UTF-8");//使用jdk的java.net.URLDecoder对URL进行解码
        } catch (UnsupportedEncodingException e) {
            try {
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            } catch (UnsupportedEncodingException e1) {
                throw new Error();
            }
        }
        if (!uri.startsWith(url)) {
            return null;
        }
        if (!uri.startsWith("/")) {
            return null;
        }
        uri = uri.replace('/', File.separatorChar);//将硬编码的文件路径分隔符替换成本地操作系统的文件路径分隔符
        if (uri.contains(File.separator + '.')//对新的uri做二次合法性校验，失败直接返回空
                || uri.contains('.' + File.separator) || uri.startsWith(".")
                || uri.endsWith(".") || INSECURE_URI.matcher(uri).matches()) {
            return null;
        }
        return System.getProperty("user.dir") + File.separator + uri;//拼接文件，使用当前运行程序所在的工程目录+uri构造绝对路径返回
    }

    private static final Pattern ALLOWED_FILE_NAME = Pattern
            .compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

    private static void sendListing(ChannelHandlerContext ctx, File dir) {/*返回文件链接相应给客户端代码*/
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);//首先创建成功的http响应消息
        response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");//设置消息头
        StringBuilder buf = new StringBuilder();//用于构造消息响应体，因为显示在浏览器，所以html格式
        String dirPath = dir.getPath();
        buf.append("<!DOCTYPE html>\r\n");
        buf.append("<html><head><title>");
        buf.append(dirPath);
        buf.append(" 目录：");
        buf.append("</title></head><body>\r\n");
        buf.append("<h3>");
        buf.append(dirPath).append(" 目录：");
        buf.append("</h3>\r\n");
        buf.append("<ul>");
        buf.append("<li>链接：<a href=\"../\">..</a></li>\r\n");
        for (File f : dir.listFiles()) {//展示根目录下的所有文件和文件夹
            if (f.isHidden() || !f.canRead()) {
                continue;
            }
            String name = f.getName();
            if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                continue;
            }
            buf.append("<li>链接：<a href=\"");
            buf.append(name);
            buf.append("\">");
            buf.append(name);
            buf.append("</a></li>\r\n");
        }
        buf.append("</ul></body></html>\r\n");
        ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);//分配对应消息的缓冲对象
        response.content().writeBytes(buffer);//将缓冲区的相应消息存放到http应答消息中
        buffer.release();//释放缓冲区
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);//将响应消息发送到缓冲区并刷新到socketChannel中
    }

    private static void sendRedirect(ChannelHandlerContext ctx, String newUri) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
        response.headers().set(LOCATION, newUri);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("Failure: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private static void setContentTypeHeader(HttpResponse response, File file) {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        response.headers().set(CONTENT_TYPE,
                mimeTypesMap.getContentType(file.getPath()));
    }
}
