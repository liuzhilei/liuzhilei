package 第四章粘包拆包.解决拆包粘包.服务端;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by liuzhilei on 2017/5/9.
 * 用于对网络事件进行读写操作
 * 解决拆包粘包现象
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

        String body = (String) msg;
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
