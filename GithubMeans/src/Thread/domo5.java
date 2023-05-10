package Thread;

public class domo5 {
    public static void main(String[] args) throws InterruptedException {
        Integer integer = 500;
        Thread t1 = new Thread(()->{
            System.out.println("t1线程开始执行");
            synchronized (integer) {
                try {
                    integer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程结束");
        });
        Thread t2 = new Thread(()->{
            synchronized (integer) {
                integer.notify();
            }
        });
        t1.start();
        Thread.sleep(5000);
        t2.start();

    }
}
