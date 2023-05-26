package Thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class domo9 {
    public static void main(String[] args) throws InterruptedException {
        /*BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        //当阻塞队列位满时，继续入队就会阻塞  直到别的线程进行出队操作
        //当阻塞队列位空时，继续出队就是阻塞  直到别的线程进行入队操作
        blockingQueue.put("hello");
        blockingQueue.put("hello1");
        blockingQueue.put("hello2");
        blockingQueue.put("hello3");
        blockingQueue.put("hello4");
        blockingQueue.put("hello5");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/

        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

        //t1线程消费元素
        Thread t1 = new Thread(()->{
            while (true) {
                try {
                    System.out.println("消费元素"+blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //t2线程生产元素
        Thread t2 = new Thread(()->{
            int val = 0;
           while (true) {
               try {
                   blockingQueue.put(val);
                   System.out.println("生产元素"+ val);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               val++;
               //每隔一秒生产一个元素
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        t1.start();
        t2.start();


    }
}
