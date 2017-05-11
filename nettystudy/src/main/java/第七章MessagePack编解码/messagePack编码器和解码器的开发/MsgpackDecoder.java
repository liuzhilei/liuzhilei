
package 第七章MessagePack编解码.messagePack编码器和解码器的开发;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by liuzhilei on 2017/5/11.
 * <p/>
 * msgPack解码器
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final byte[] array;
        final int length = msg.readableBytes();
        array = new byte[length];
        //从数据报中获取需要解码的byte数组
        msg.getBytes(msg.readerIndex(), array, 0, length);
        MessagePack messagePack = new MessagePack();
        //调用messagePack的read方法将其反序列化object对象，然后加入到解码列表out中，就完成了解码操作
        out.add(messagePack.read(array));
    }
}
