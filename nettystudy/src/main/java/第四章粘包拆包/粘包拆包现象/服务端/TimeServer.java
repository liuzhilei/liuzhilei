package 第四章粘包拆包.粘包拆包现象.服务端;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by liuzhilei on 2017/5/9.
 * 服务端粘包拆包测试
 *
 * 执行结果表明只接收到两条消息，第一条包含57条"QUERY TIME ORDER"指令，第二条
 * 包含43条"QUERY TIME ORDER"指令，正好100条。但是我们期待的是收到100条消息，所以
 * 发生了tcp粘包
 */
public class TimeServer {
    public void bind(int port) throws Exception {
        //配置服务端的NIO线程组，NioEventLoopGroup是一个线程组，包含了一组nio线程，专门用于网络事件的处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap 是用于启动NIO服务端的辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();

            /**
             * group，将两个NIO线程组当作入参传递到ServerBootstrap，为主从reactor模型。
             * bossGroup就是主reactor，用于响应连接请求
             * workerGroup就是从reactor，用于处理IO操作请求
             */
            bootstrap.group(bossGroup, workerGroup)
                    //设置创建的Channel为NioServerSocketChannel，功能对应于jdk nio中的ServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                            //配置NioServerSocketChannel的TCP参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                            //绑定IO时间的处理类ChildChannelHandler，主要用于处理网络IO事件，例如记录日志，对消息进行编解码等
                    .childHandler(new ChildChannelHandler());

            /**
             * 服务器启动类ServerBootstrap配置完成以后，调用他的bind方法绑定监听端口；随后，调用
             * 他的同步阻塞方法sync()等待绑定操作完成，完成之后返回一个channelFuture，他类似于jdk的
             * java.util.concurrent.Future，主要用于异步操作的通知回调
             */
            ChannelFuture future = bootstrap.bind(port).sync();

            //使用该方法进行阻塞，等待服务端链路关闭之后main函数才退出
            future.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 10186;
        /*if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/

        new TimeServer().bind(port);
    }


}
