package threads.Interrupts;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 7/2/16.
 */
class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        }catch (InterruptedException e){
            System.out.println("InterruptedException");
        }
        System.out.println("exiting sleepblocked.run()");
    }
}

class IOBlocked implements Runnable{

    private InputStream in;
    public IOBlocked(InputStream is){
        in = is;
    }
    @Override
    public void run() {
        try {
            System.out.println("Waiting for read(): ");
            in.read();
        }catch (IOException e){
            if(Thread.currentThread().isInterrupted())
                System.out.println("Interrupted from blocked IO");
            else
                throw new RuntimeException();
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable{

    public synchronized void f(){
        while (true)
            Thread.yield();

    }

    public SynchronizedBlocked(){
        new Thread(){
            @Override
            public void run() {
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("trying to call f()");
        f();
        System.out.println("exiting SynchronizedBlocked.run()");
    }
}

public class Interrupting{
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    static void test(Runnable r){
        try{
            Future<?> f = executorService.submit(r);
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("Interrupting " + r.getClass().getName());
            f.cancel(true);
            System.out.println("Interrupting sent to " + r.getClass().getName());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}