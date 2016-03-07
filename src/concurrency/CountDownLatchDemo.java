package concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 1/3/16.
 */
class TaskPortion implements Runnable{

    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;
    TaskPortion(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void doWork() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return Integer.toString(id);
        //return String.format("%1$-3d" + id);
    }
}
class WaitingTask implements Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;
    WaitingTask(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }

    @Override
    public String toString() {
        return "Waiting " + Integer.toString(id);
    }
}
public class CountDownLatchDemo {

    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i=0;i<10;i++)
            exec.execute(new WaitingTask(latch));
        for (int i=0;i<SIZE;i++)
            exec.execute(new TaskPortion(latch));
        System.out.println("Launched all tasks");
        exec.shutdown();
    }

}















