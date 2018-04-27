package com.chenhe;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author chenhe
 * @Date 2018-04-27 15:42
 * @desc
 **/
public class TestHashMap {

    @Test
    public void test(){
        HashMap map = new HashMap(1);
        for (int i = 0 ; i< 100; i++){
            map.put(i,i);
        }
        System.out.println(map);
    }
}
