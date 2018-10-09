package com.chenhe;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author chenhe
 * @Date 2018-06-16 11:59
 * @desc
 **/
public class Test {

    @org.junit.Test
    public void test() {
        ByteBuf byteBuf = Unpooled.buffer();
        System.out.println(byteBuf);

        byteBuf.writeInt(2);
        System.out.println(byteBuf);

        byteBuf.readByte();
        System.out.println(byteBuf);

        byteBuf.writeInt(3);
        System.out.println(byteBuf);


        byteBuf.readByte();
        System.out.println(byteBuf);

        ByteBuf byteBuf1 = Unpooled.directBuffer();
        System.out.println(byteBuf1);

        byteBuf1.writeInt(4);
        System.out.println(byteBuf1);

    }
}
