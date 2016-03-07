package threads.producer.consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 1/3/16.
 */
public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Producer thread running...");
            wait();
            System.out.println("Resumed.");
        }
    }

    public void consume() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        TimeUnit.SECONDS.sleep(2);

        synchronized (this){
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Returned key pressed.");
            notify();
        }

    }
}

class App1{

    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
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

class ProcessorCP{

    private List<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;

    public void produce() throws InterruptedException{

    }

    public void consume() throws InterruptedException{

    }


}

























