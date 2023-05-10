package Thread;

public class domo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println("hello start");
            }
            System.out.println("线程结束");
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
//    public  static boolean flag = false;
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(()->{
//            while (!flag) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("hello start");
//            }
//            System.out.println("线程结束");
//        });
//        thread.start();
//        Thread.sleep(5000);
//        flag = true;
//    }
}
