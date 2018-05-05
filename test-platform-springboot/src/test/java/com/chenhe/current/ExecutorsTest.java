package com.chenhe.current;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenhe
 * @Date 2018-05-02 15:20
 * @desc
 **/
public class ExecutorsTest {

    @Test
    public void executor(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0 ; i<10; i++){
        }
    }
}
