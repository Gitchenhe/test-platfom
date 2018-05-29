package com.chenhe.channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author chenhe
 * @Date 2018-05-08 16:37
 * @desc
 **/
public class ChannelTest {

    @Test
    public void test() throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 1234);
        serverSocketChannel.bind(socketAddress);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey>  keys =  selector.selectedKeys();
            for (SelectionKey key : keys){
                SocketChannel socketChannel =((ServerSocketChannel)key.channel()).accept();
                System.out.println("客户端连接: " + socketChannel.getRemoteAddress());
                ClientServer.getInstance().addClient(socketChannel);
            }
            System.out.println("------------");
        }
    }

    static class ClientServer implements Runnable{
        private static List<SocketChannel>  socketChannelList;
        private static Selector selector ;
        private static ClientServer instance;

        private ClientServer(){}

        {
            try {
                selector = Selector.open();
                socketChannelList = new ArrayList<>();
            } catch (IOException e) {
                System.out.println("selector 初始化失败");
                e.printStackTrace();
            }
        }

        public static ClientServer getInstance(){
            if (instance == null){
                instance = new ClientServer();
                new Thread(instance).start();
            }
            return instance;
        }

        public static void addClient(SocketChannel socketChannel){
            try {
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            } catch (ClosedChannelException e) {
                System.out.println("向selector中注册channel失败");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("向selector中注册channel失败");
                e.printStackTrace();
            }
            socketChannelList.add(socketChannel);
        }

        @Override
        public void run() {
            boolean runFlag = true;
            while (runFlag){
                try {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    for (SelectionKey key : keys){
                        if(key.isReadable()){
                            SocketChannel channel = (SocketChannel)key.channel();
                            System.out.println(Thread.currentThread().getName()+" 可读: remoteAddress = "+ channel.getRemoteAddress());
                            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                            int length = channel.read(byteBuffer);
                            while (length > 0){
                                byteBuffer.flip();
                                while (byteBuffer.hasRemaining()){
                                    System.out.print( (char) byteBuffer.get());
                                }
                                byteBuffer.clear();
                                length = channel.read(byteBuffer);
                            }
                        }
                        if (key.isWritable()){
                            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                            SocketChannel channel = (SocketChannel)key.channel();
                            System.out.println(Thread.currentThread().getName()+" 可写: remoteAddress = "+ channel.getRemoteAddress());
                            byteBuffer.clear();
                            byteBuffer.put("接收到消息".getBytes());
                            channel.write(byteBuffer);
                            channel.close();
                        }

                    }
                } catch (IOException e) {
                    runFlag = false;
                    e.printStackTrace();
                }

            }
        }
    }

}
