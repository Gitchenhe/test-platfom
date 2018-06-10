package com.chenhe.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author chenhe
 * @Date 2018-06-07 15:05
 * @desc
 **/
public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("准备运行端口:" + port);

        try {
            /**
             *  ServerBootstrap 是一个启动NIO服务的辅助启动类.可以在这个类中使用channel
             */
            ServerBootstrap b = new ServerBootstrap();
            /**
             * 这一步是必须的,如果没有group将会报java.lang.IllegalStateException: group not
             * set异常
             */
            b = b.group(bossGroup, workerGroup);

            /**
             * ServerSocketChannel以NIO的selector为基础实现的,用来接收新的连接,这里告诉Channel如何获取新的连接
             */
            b = b.channel(NioServerSocketChannel.class);
            /**
             * 这里的事件处理类经常会被用来处理一个最近的已经接收的channel.ChannelInitializer是一个特殊的处理类,
             * 他的目的是帮助使用者配置一个新的Channel.
             * 也许你想通过增加一些处理类,比如NettyServerHandler来配置一个新的Channel
             * 或者其对应的ChannelPipeline来实现你的网络程序,当你的程序变得复杂时,可能你会增加更多的处理类到pipline上,
             * 然后提取这些匿名类到最顶层的类上.
             *
             */
            b = b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new DiscardServerHandler());
                }
            });
            /**
             * 你可以设置这里指定的通道实现的配置参数,我们正在写一个TCP/IP的服务端,
             * 因此我们被允许设置socket的参数选项,比如tcpNoDelay和keepAlive
             * 请参考ChannelOption和详细的ChannelConfig实现的接口文档,以此可以对ChannelOptions有一个大概的认识.
             */
            b = b.option(ChannelOption.SO_BACKLOG, 128);
            /**
             * option()是提供给NioServerSocketChannel用来接收新的连接,
             * childOption()是提供给由父福安到ServerChannel接收到的连接
             * 在这个例子中也是NioServerSocketChannel。
      q       */
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);

            /**
             * 绑定端口并启动去接收新来的连接
             */
            ChannelFuture channelFuture = b.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 1111;
        new DiscardServer(port).run();
        System.out.println("server:run()");
    }
}
