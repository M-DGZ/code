package Thread;

class MyBlockingQueue {
    private int[] elem = new int[1000];
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    public synchronized void put(int val) throws InterruptedException {

        while (size == elem.length) {
            //满了
            this.wait();
        }
       elem[tail++] = val;
        if(tail == elem.length) {
            tail = 0;
        }
        size++;
        this.notify();
    }
    public synchronized int task() throws InterruptedException {
        while (size == 0) {
            //空的
            this.wait();
        }
        int data = elem[head];
        head++;
        size--;
        if(head == elem.length) {
            head = 0;
        }
        this.notify();
        return data;
    }
}
public class domo10 {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue();
        //消费者
        Thread t1 = new Thread(()->{
            while(true) {
                try {
                    System.out.println("消费元素"+myBlockingQueue.task());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //生产者
        Thread t2 = new Thread(()->{
            int val = 0;
            while(true) {
                try {
                    System.out.println("生产元素"+val);
                    myBlockingQueue.put(val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                val++;
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
