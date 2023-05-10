package Thread;

public class domo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            System.out.println("1");
        });
        thread.start();
        thread.join();
        System.out.println("2");

        System.out.println(thread.getState());
    }
}
