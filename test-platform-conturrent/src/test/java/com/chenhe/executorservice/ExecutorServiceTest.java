package com.chenhe.executorservice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author chenhe
 * @Date 2018-05-04 11:11
 * @desc
 **/
public class ExecutorServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //异步执行
        executorService.execute(new ExecutorServiceRunnable());
        executorService.execute(new ExecutorServiceRunnable());

        //也是异步执行,但是它返回一个Future,通过Future判断它是否执行完成
        Future future1 = executorService.submit(new ExecutorServiceRunnable());
        Future future2 = executorService.submit(new ExecutorServiceRunnable());
        //判断是否执行完成
        future1.get();
        future2.get();

        //异步执行Callable,返回执行结果
        Future<Integer> future3 = executorService.submit(new ExecutorServiceCallable(1));
        Future<Integer> future4 = executorService.submit(new ExecutorServiceCallable(2));

        Integer a = future3.get();
        Integer b = future4.get();

        System.out.println("a = " + a + ", b = " + b);


        Set<Callable<String>> callables = new HashSet<>();

        callables.add(() -> {return "task1";});
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        String result = executorService.invokeAny(callables);

    }

    static class ExecutorServiceRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 执行!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ExecutorServiceCallable implements Callable<Integer> {
        Integer x;

        public ExecutorServiceCallable(Integer x) {
            this.x = x;
        }

        @Override
        public Integer call() throws Exception {
            return ++x;
        }
    }



}
