package com.chenhe.buffer;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;

/**
 * @author chenhe
 * @Date 2018-05-08 15:53
 * @desc
 **/
public class BufferTest {
    @Test
    public void test() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\chenhe\\Desktop\\易通支付\\辅助文件-支付网关类业务\\demo\\易通支付商户客户端示例程序--jsp版\\etonepay_sdk\\readme.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        //写入数据到buffer
        int bytesRead = fileChannel.read(byteBuffer);

        while (bytesRead != -1){
            System.out.println("Read: " + bytesRead);
            //反转,从写模式,切换到读模式
            byteBuffer.flip();

            //从buffer读取数据
            while (byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }

            //清空buffer,全部清空,compact():清空已经读过的数据
            byteBuffer.clear();
            bytesRead = fileChannel.read(byteBuffer);
        }

        fileChannel.close();
    }

    @Test
    public void testBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        byteBuffer.put("hello".getBytes());
        System.out.println("前: byteBuffer.capacity()="+ byteBuffer.capacity()+",byteBuffer.limit() = "+byteBuffer.limit()+", byteBuffer.position() = "+byteBuffer.position());
        byteBuffer.flip();
        System.out.println("后: byteBuffer.capacity()="+ byteBuffer.capacity()+",byteBuffer.limit() = "+byteBuffer.limit()+", byteBuffer.position() = "+byteBuffer.position());
        System.out.println("hello".getBytes());
        while (byteBuffer.hasRemaining()){
            byteBuffer.get();
            System.out.println("byteBuffer.capacity()="+ byteBuffer.capacity()+",byteBuffer.limit() = "+byteBuffer.limit()+", byteBuffer.position() = "+byteBuffer.position());
        }
        byteBuffer.clear();

        System.out.println("-------测试CharBuffer-------");
        CharBuffer charBuffer  = CharBuffer.allocate(20);
        charBuffer.put("hello");
        charBuffer.flip();
        System.out.println(charBuffer);
        System.out.println(charBuffer.get());

        System.out.println("-------测试ShortBuffer-------");
        ShortBuffer shortBuffer = ShortBuffer.allocate(20);
        shortBuffer.put((short)10);
        shortBuffer.flip();
        System.out.println("short buffer = " +shortBuffer);
        System.out.println(shortBuffer.get());

        System.out.println("---------longBuffer----------");
        LongBuffer longBuffer = LongBuffer.allocate(10);
        longBuffer.put(50L);
        longBuffer.put(60L);
        longBuffer.flip();
        System.out.println("longBuffer: " + longBuffer);
        System.out.println(longBuffer.get());
    }
    @Test
    public void test2(){
        System.out.println("----------测试缓存分配--------");
        System.out.println("分配前:" + Runtime.getRuntime().freeMemory());

        // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
        // 要超过多少内存大小JVM才能感觉到？
        ByteBuffer buffer = ByteBuffer.allocate(102400);
        System.out.println("buffer = " + buffer);

        System.out.println("分配后: " + Runtime.getRuntime().freeMemory());

        // 这部分直接用的系统内存，所以对JVM的内存没有影响
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("直接分配系统内存后: " + Runtime.getRuntime().freeMemory());

        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);

        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);
    }
}
