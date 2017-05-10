package 第四章粘包拆包.解决拆包粘包.客户端;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzhilei on 2017/5/9.
 * 解决拆包粘包现象
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeClientHandler.class);

    private int counter;

    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    /**
     * 客户端和服务端tcp链路建立成功以后，netty的nio线程会调用channelActive方法
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //将请求消息发送给服务端
        ByteBuf message = null;
        /**
         * 循环发送100条消息，每发送一条就刷新一次，保证每条消息都会被写入channel中。
         * 按照设计，服务端应该会接收到100条查询时间指令的请求消息
         */
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    /**
     * 当服务端返回应答消息时，channelRead方法被调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        //客户端每接收到服务端一条应答消息之后，就打印一次计数器，按照设计应该会打印100次服务端的系统时间
        System.out.println("now is :" + body + " ; the counter is :" + (++counter));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常时，打印异常，释放客户端资源
        LOGGER.error("exception : {}", cause.getMessage());
        ctx.close();
    }
}
