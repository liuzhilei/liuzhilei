package 第七章MessagePack编解码.messagePack编码器和解码器的开发;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import 第六章编解码技术.java序列化.UserInfo;

/**
 * Created by liuzhilei on 2017/5/11.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    /**
     * 客户端与服务端tcp链接成功以后，会触发此方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos = UserInfo();
        for (UserInfo info : infos) {
            ctx.write(info);
        }
        ctx.flush();
    }

    private UserInfo[] UserInfo() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setUserID(i);
            userInfo.setUserName("ABCDEFG --->" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }

    /**
     * 服务端返回应答消息，会调用此方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接收到msgpack的消息：" + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
