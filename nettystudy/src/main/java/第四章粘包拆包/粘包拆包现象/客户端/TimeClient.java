package 第四章粘包拆包.粘包拆包现象.客户端;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;

/**
 * Created by liuzhilei on 2017/5/9.
 * 客户端粘包拆包测试
 *
 * 客户端输出结果：
 * now is :BAD ORDER
 * BAD ORDER ; the counter is :1
 *
 * 按照设计客户端应该收到100条当前系统时间的消息，但实际上只收到一条，原因是
 * 服务端只收到两条请求消息，所以实际服务端也只是发送两条应答，因为不满足查询
 * 条件，所以返回2条"BAD ORDER"应答消息，说明服务端返回的应答消息也发生了粘包
 */
public class TimeClient {
    public void connect(int port, String host) throws Exception {
        //配置客户端NIO线程组（创建客户端处理IO读写的NioEventLoopGroup线程组）
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端辅助启动类
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            //发起异步链接操作，然后调用sync同步方法等待连接成功
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            //等待客户端链路关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 10186;
        new TimeClient().connect(port, InetAddress.getLocalHost().getHostAddress());
    }
}
