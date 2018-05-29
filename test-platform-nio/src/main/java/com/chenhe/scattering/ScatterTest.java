package com.chenhe.scattering;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author chenhe
 * @Date 2018-05-11 14:39
 * @desc
 **/
public class ScatterTest {

    public static void main(String[] args) throws IOException {
        String path ="C:\\Users\\chenhe\\Desktop\\易通支付\\辅助文件-支付网关类业务\\demo\\易通支付商户客户端示例程序--jsp版\\etonepay_sdk\\readme.txt";
        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\chenhe\\Desktop\\易通支付\\辅助文件-支付网关类业务\\demo\\易通支付商户客户端示例程序--jsp版\\etonepay_sdk\\readme.txt", "rw");

        ByteBuffer header = ByteBuffer.allocate(48);
        ByteBuffer body = ByteBuffer.allocate(48);

        ByteBuffer[] byteBuffers = {header, body};
        FileChannel channel = randomAccessFile.getChannel();

        channel.read(byteBuffers);

    }

}
