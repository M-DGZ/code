package Thread;

import java.util.Scanner;

class Conner{
    private int count = 0;
    public void setCount () {
        synchronized (this) {
            count++;
        }

    }
    public int getCount () {
        return count;
    }
}
public class domo4 {
    volatile public static int tmp = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (tmp == 0) {
            }
            System.out.println("线程结束");
        });
        Thread t2 = new Thread(()->{
            System.out.println("请输入一个让T1线程结束的标志");
            Scanner scanner = new Scanner(System.in);
            tmp = scanner.nextInt();
        });
        t1.start();
        t2.start();
    }
//    public static void main(String[] args) throws InterruptedException {
//        Conner conner = new Conner();
//        Thread t1 = new Thread(()->{
//            for (int i = 0; i < 5000; i++) {
//                conner.setCount();
//            }
//        });
//
//        Thread t2 = new Thread(()->{
//            for (int i = 0; i < 5000; i++) {
//                conner.setCount();
//            }
//        });
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(conner.getCount());
//    }
}
