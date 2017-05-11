package 第五章分隔符和定长解码器的应用.DelimiterBasedFrameDecoder开发.服务端;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by liuzhilei on 2017/5/11.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("这是第 " + (++counter) + "次，接收到客户端发来的消息：" + body);
        //给返回客户端的消息手动添加分隔符
        body += "$_";
        ByteBuf buf = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端发生了异常");
        cause.printStackTrace();
        ctx.close();
    }
}
