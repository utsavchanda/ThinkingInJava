package threads.producer.consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 1/3/16.
 */
public class App {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    private static void producer(){
        Random rand = new Random(47);
        while (true){
            queue.add(rand.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random rand = new Random();
        while (true){
            TimeUnit.MILLISECONDS.sleep(100);
            if (rand.nextInt() == 0){
                Integer value = queue.take();
                System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                producer();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
