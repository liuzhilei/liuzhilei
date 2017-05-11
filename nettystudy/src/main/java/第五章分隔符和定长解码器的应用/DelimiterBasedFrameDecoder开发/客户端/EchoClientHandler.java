package 第五章分隔符和定长解码器的应用.DelimiterBasedFrameDecoder开发.客户端;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.jar.Pack200;

/**
 * Created by liuzhilei on 2017/5/11.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private int counter;
    static final String echo_req = "hello, liuzhilei, welcome to netty.$_";

    /**
     * 客户端和服务端链接成功以后，会执行该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //循环发送请求消息给服务端
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(echo_req.getBytes()));
        }
    }

    /**
     * 接收到服务端相应，会执行该方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("这是第" + (++counter) + "次，客户端接收到服务端相应的消息：" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
