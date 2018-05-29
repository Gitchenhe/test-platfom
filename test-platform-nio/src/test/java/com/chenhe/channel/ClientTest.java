package com.chenhe.channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author chenhe
 * @Date 2018-05-08 17:32
 * @desc
 **/
public class ClientTest {

    @Test
    public void test(){
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",1234);
        try {
            SocketChannel socketChannel = SocketChannel.open(socketAddress);
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            byteBuffer.put("你好".getBytes());
            while(byteBuffer.hasRemaining()){
                socketChannel.write(byteBuffer);
            }

            byteBuffer.flip();
            int length = socketChannel.read(byteBuffer);
            while (length != -1){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.print( (char) byteBuffer.get());
                }
                byteBuffer.flip();
                length = socketChannel.read(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
