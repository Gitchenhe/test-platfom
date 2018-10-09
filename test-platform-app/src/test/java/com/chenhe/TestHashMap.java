package com.chenhe;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author chenhe
 * @Date 2018-04-27 15:42
 * @desc
 **/
public class TestHashMap {

    @Test
    public void test() {
        HashMap map = new HashMap(1);
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        System.out.println(map);
    }


    @Test
    public void test2() {
        int data[] = {1,3,3,4,5,6,6,7,8,1,3,5,2,6,3,6,3,2,9};
        Arrays.sort(data);
        System.out.println(count(data));
    }

    private int count(int[] data) {
        int result = data[0];
        int count = 1;
        for (int i = 1; i < data.length; i++) {
            if (result == data[i]) {
                count++;
            } else {
                count--;
            }
            if (count <= 0) {
                result = data[i];
            }
        }
        return result;

    }
}
