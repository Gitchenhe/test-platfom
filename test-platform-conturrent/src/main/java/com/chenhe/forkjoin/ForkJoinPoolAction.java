package com.chenhe.forkjoin;

import java.security.Policy;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-08-29 15:22
 * @desc
 **/
public class ForkJoinPoolAction {
    public static void main(String[] args) {
        PrintTask printTask = new PrintTask(0, 300);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(printTask);


        forkJoinPool.awaitQuiescence(2, TimeUnit.SECONDS);

        forkJoinPool.shutdown();
    }

    static class PrintTask extends RecursiveAction {
        private static final int THRESHOLD = 50; //最多只能打印50个数
        private int start;
        private int end;

        public PrintTask(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start < THRESHOLD) {
                System.out.println(Thread.currentThread().getName() + "的i值：" + start + " ~ " + end);
            } else {
                int middle = (start + end) / 2;
                PrintTask left = new PrintTask(start, middle);
                PrintTask right = new PrintTask(middle, end);

                left.fork();
                right.fork();
            }
        }
    }
}
