package com.chenhe.blockingqueue.arrayblockingqueue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-05-02 15:30
 * @desc BlockingQueue通常用于一个线程生产对象, 而另外一个线程消费这些对象的场景
 * 一个线程持续的生产对象,并插入到队列 直到队列的临界值.
 * 到达临界值后,生产线程在往里插入新对象时,发生阻塞. 消费线程在队列为空时,也会阻塞.
 * ---------------------------------------------------------------------
 * |  方法  |  抛异常    |  特定值    |  阻塞    |            超时          |
 * |  插入  |  add(o)   |  offer(o) | put(o)  | offer(o,timeout,timeunit)|
 * |  移除  | remove(o) |  poll()   | take(o) | poll(o,timeout,timeunit) |
 * |  检查  | element(o)|  peek(o)  |         |                          |
 * ----------------------------------------------------------------------
 **/
public class BlockQueueTest {


    /**
     * ArrayBlockingQueue类是一个有界阻塞队列,内部实现是将对象放到一个数组里. 也就意味着,他不能够存取无限多的元素,
     * 它内部是基于数组实现的,就是说它具有数组的特性,一旦初始化,大小就无法修改.
     */
    @Before
    public void info(){
        System.out.println("所有的测试都是基于ArrayBlockingQueue");
        System.out.println();
    }

    @Test(expected = IllegalStateException.class)
    public void testAdd() {
        int queueCapacity = 2;
        BlockingQueue queue = new ArrayBlockingQueue(queueCapacity);
        System.out.println("BlockingQueue add 方法,超出队列临界值,报异常");

        System.out.println("queue capacity = " + queueCapacity);

        for (int i = 1; i < 100; i++) {
            System.out.println("插入第" + i + "个元素,结果:" + queue.add(i));
        }
    }

    @Test
    public void testOffer() throws InterruptedException {

        System.out.println("BlockingQueue offer 方法, 超出队列临界值,返回false, 不报异常");
        int queueCapacity = 2;
        BlockingQueue queue = new ArrayBlockingQueue(queueCapacity);
        for (int i = 1; i < 100; i++) {
            System.out.println("插入第" + i + "个元素,结果:" + queue.offer(i, 1,TimeUnit.SECONDS));
        }

        Object object;
        while ((object = queue.poll()) != null) {
            System.out.println(object);
        }
    }

    @Test
    public void testPut() throws InterruptedException {
        System.out.println("BlockingQueue put 方法, 超出队列临界值,阻塞");
        int queueCapacity = 2;
        BlockingQueue queue = new ArrayBlockingQueue(queueCapacity);
        for (int i = 1; i < 100; i++) {
            System.out.println("插入第" + i + "个元素");
            queue.put(i);
        }

        Object object;
        while ((object = queue.poll()) != null) {
            System.out.println(object);
        }
    }
    @Test(expected = NoSuchElementException.class)
    public void testElement() throws InterruptedException {
        System.out.println("BlockingQueue element 方法, 如果队列为空, 报异常");
        int queueCapacity = 2;
        BlockingQueue queue = new ArrayBlockingQueue(queueCapacity);
        queue.element();
    }
}
