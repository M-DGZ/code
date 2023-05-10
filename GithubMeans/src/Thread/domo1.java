package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread extends Thread {
    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello t1");
        }
    }
}

class MyThread1 implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello t2");
        }
    }
}
public class domo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hello t3");
                }
            }
        });
        thread.start();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hello t4");

                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(()->{
           while (true) {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("hello t5");
           }
        });
        thread2.start();


        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("call");
                return 1;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        Thread thread3 = new Thread(futureTask);
        thread3.start();
        System.out.println(futureTask.get());
    }
//    public static void main(String[] args) {
//        MyThread1 mythread1 = new MyThread1();
//        Thread thread = new Thread(mythread1);
//        thread.start();
//    }
}
    // public static void main(String[] args) {
//        Thread t1 = new MyThread();
//        t1.start();
//
//        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("hello main");
//        }
//    }