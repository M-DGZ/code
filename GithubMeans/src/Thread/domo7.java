package Thread;

class Count {
    int count = 0;
    public void add() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
public class domo7 {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = Thread.currentThread();  //获取当前线程的引用
//        System.out.println(thread.getName()); //通过getName来获取到当前线程的名称
//        //线程的状态是一个枚举类型
//        for (Thread.State state: Thread.State.values()) {
//            System.out.println(state);
//        }
        Count count = new Count();
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 10000; i++) {
                count.add();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i = 0; i < 10000; i++) {
                count.add();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count.getCount());
    }
}
