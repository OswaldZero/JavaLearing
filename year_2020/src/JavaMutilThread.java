public class JavaMutilThread {
    public static void main(String[] args) {
        Test test=new Test();
        test.start();
    }
}
class Test extends Thread{
    @Override
    public void run() {
        System.out.println("Thread start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread end");
    }
}
