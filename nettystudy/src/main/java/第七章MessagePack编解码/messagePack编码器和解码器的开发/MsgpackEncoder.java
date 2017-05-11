package 第七章MessagePack编解码.messagePack编码器和解码器的开发;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created by liuzhilei on 2017/5/11.
 * <p/>
 * msgPack编码器
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        MessagePack messagePack = new MessagePack();
        //将object类型的pojo对象编码成byte数组
        byte[] write = messagePack.write(msg);
        out.writeBytes(write);
    }
}
