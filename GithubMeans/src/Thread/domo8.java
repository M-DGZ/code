package Thread;

public class domo8 {
    public static void main(String[] args) throws InterruptedException {


        Object object = new Object();
        Thread t1 = new Thread(()->{
            System.out.println("wait()1开始执行");
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("wait()1执行结束");
        });

        Thread t2 = new Thread(()->{
            System.out.println("wait()2开始执行");
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("wait()2执行结束");
        });

        t1.start();
        t2.start();
        Thread.sleep(1000);
        synchronized (object) {
            object.notify();
        }
    }
}
