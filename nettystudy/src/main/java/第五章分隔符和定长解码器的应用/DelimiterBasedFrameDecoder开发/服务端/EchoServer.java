package 第五章分隔符和定长解码器的应用.DelimiterBasedFrameDecoder开发.服务端;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by liuzhilei on 2017/5/11.
 * <p/>
 * DelimiterBasedFrameDecoder 是以分隔符作为结束标志的消息解码器
 * <p/>
 * 以echo为例，消息以"$_"作为分隔符
 */
public class EchoServer {

    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //创建分隔符缓冲对象
                            ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
                            /**
                             * 自动对请求消息进行解码
                             * 创建DelimiterBasedFrameDecoder对象，加入ch.pipeline()中。单条最大长度1024，超过这个长度仍未找到分隔符，就抛出异常
                             */
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
                            //StringDecoder将byteBuf解码成字符串对象
                            ch.pipeline().addLast(new StringDecoder());
                            //处理接收到的msg消息，接收到的msg消息经过解码已经是字符串对象了
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            //绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception {
        int port = 10087;
        new EchoServer().bind(port);
    }
}
