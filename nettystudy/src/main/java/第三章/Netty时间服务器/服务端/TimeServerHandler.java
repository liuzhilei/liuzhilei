package 第三章.Netty时间服务器.服务端;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by liuzhilei on 2017/5/9.
 * 用于对网络事件进行读写操作
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf类似于jdk中的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        //buf.readableBytes()获取缓冲区可读的字节数，
        byte[] req = new byte[buf.readableBytes()];
        //将buf中的字节数组复制到新的byte数组中
        buf.readBytes(req);
        //获取请求的信息
        String body = new String(req, "utf-8");
        System.out.println("the time server receive order : " + body);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //异步发送应答消息给客户端
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**
         * 将消息发送队列中的消息写入到socketChannel中发送给对方。
         * 从性能角度考虑，为了防止频繁的唤醒selector进行消息发送，netty的write方法并不直接将消息
         * 写入socketChannel中，调用write方法只是把待发送的消息放到发送缓冲数组中，再通过调用flush
         * 方法，将发送缓冲区中的消息全部写到SocketChannel中
         */
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常时，关闭ChannelHandlerContext
        System.out.println("发生了异常");
        ctx.close();
    }
}
