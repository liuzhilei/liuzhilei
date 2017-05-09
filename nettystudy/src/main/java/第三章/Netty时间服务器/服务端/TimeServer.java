package 第三章.Netty时间服务器.服务端;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by liuzhilei on 2017/5/9.
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
             * bossGroup就是主reactor，用于相应连接请求
             * workerGroup就是从reactor，用于处理IO操作请求
             */
            bootstrap.group(bossGroup, workerGroup)
                    //设置创建的Channel为NioServerSocketChannel，功能对应于jdk nio中的ServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                            //配置NioServerSocketChannel的TCP参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                            //绑定IO时间的处理类ChildChannelHandler，主要用于处理网络IO事件，例如记录日志，对消息进行编解码等
                    .childHandler(new ChildChannelHandler());

            //绑定端口，同步等待成功。返回一个ChannelFuture，类似jdk中的future，用于异步操作的通知回调
            ChannelFuture future = bootstrap.bind(port).sync();

            //等待服务器监听端口关闭。该方法会阻塞，等待服务端链路关闭之后main函数才退出
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
        int port = 10086;
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
