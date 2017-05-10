package 第四章粘包拆包.粘包拆包现象.服务端;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by liuzhilei on 2017/5/9.
 * 用于对网络事件进行读写操作
 * 模拟拆包粘包现象
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;

    /**
     * 服务端接收到客户端的消息时，会执行该方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf类似于jdk中的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        //buf.readableBytes()获取缓冲区可读的字节数，
        byte[] req = new byte[buf.readableBytes()];
        //将buf中的字节数组复制到新的byte数组中
        buf.readBytes(req);
        /**
         * 获取请求的信息
         * 每读到一次消息，计数器就+1
         * 服务器接收到的消息总数应该跟客户端发送的消息总数相同
         */
        String body = new String(req, "utf-8").substring(0, req.length
                //System.getProperty("line.separator") 是换行符的意思，屏蔽了windows和linux的区别
                - System.getProperty("line.separator").length());
        System.out.println("the time server receive order : " + body
                + " ; the counter is : " + (++counter));
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //异步发送应答消息给客户端
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常时，关闭ChannelHandlerContext
        System.out.println("发生了异常");
        ctx.close();
    }
}
