package threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 1/3/16.
 */

class Processor implements Runnable{
    private CountDownLatch latch;
    private int id;

    public Processor(CountDownLatch latch, int idn){
        this.latch = latch;
        id = idn;
    }
    @Override
    public void run(){
        System.out.println("Started id: " + latch.getCount() + " " + id);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}

public class CountDownLatchEx {

    public static void main(String[] args) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for (int i=0;i<3;i++){
            exec.submit(new Processor(latch, i));
        }

        latch.await();
        System.out.println("Completed");
        exec.shutdown();
    }
}
