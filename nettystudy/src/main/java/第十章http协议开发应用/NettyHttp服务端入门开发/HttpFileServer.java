package 第十章http协议开发应用.NettyHttp服务端入门开发;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by liuzhilei on 2017/5/12.
 * http文件服务器 启动类
 */
public class HttpFileServer {

    private static final String DEFAULT_URL = "/nettystudy/src/main/java";
    //private static final String DEFAULT_URL = "/nettystudy/src/test";

    public void run(final int port, final String url) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //向channelPipeline中添加http请求消息解码器
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            /**
                             * 添加HttpObjectAggregator解码器
                             * 它的作用是将多个消息转换为单一的FullHttpRequest或者FullHttpResponse，原因
                             * 是http解码器在每个http消息中会生成多个消息对象：
                             * 1.httpRequest/httpResponse
                             * 2.httpContent
                             * 3.lastHttpContent
                             */
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            //添加http响应消息解码器
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            /**
                             * 添加Chunked handler，它的作用是支持异步发送大的码流（例如大的文件传输），但
                             * 不占用过多的内存，防止发生java内存溢出错误
                             */
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            //HttpFileServerHandler 自定义的文件服务器业务逻辑处理
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
                        }
                    });

            //ChannelFuture future = bootstrap.bind("127.0.0.1", port).sync();
            //String ip = InetAddress.getLocalHost().getHostAddress();
            String ip = "127.0.0.1";
            ChannelFuture future = bootstrap.bind(ip, port).sync();
            System.out.println("http 文件目录服务器启动，网址是：http://" + ip + ":" + port + url);
            future.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        String url = DEFAULT_URL;
        new HttpFileServer().run(port, url);
    }

}
