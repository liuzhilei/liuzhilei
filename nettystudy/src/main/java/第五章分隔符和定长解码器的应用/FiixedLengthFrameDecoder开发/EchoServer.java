package 第五章分隔符和定长解码器的应用.FiixedLengthFrameDecoder开发;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by liuzhilei on 2017/5/11.
 * <p/>
 * FixedLengthFrameDecoder是固定长度解码器，能够按照指定的长度对消息进行自动解码，不需要考虑
 * tcp粘包拆包问题
 * 利用FixedLengthFrameDecoder解码器，无论一次接收多少数据报，都会按照构造函数中设置的固定长度进行解码，
 * 如果是半包数据，FixedLengthFrameDecoder会缓存半包消息并等待下个包到达以后进行拼包，知道读取到一个完整的包
 * <p/>
 * 该类的测试方法，先启动这个server，然后cmd打开dos，telnet localhost 10088 就可以测试了
 */
public class EchoServer {
    public void bind(int port) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(5));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 10088;
        new EchoServer().bind(port);
    }
}
