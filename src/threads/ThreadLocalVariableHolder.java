package threads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 5/2/16.
 */

class Accessor implements Runnable{

    private final int id;
    public Accessor(int idn){
        id = idn;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }
}

public class ThreadLocalVariableHolder {

    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random random = new Random(47);
        protected synchronized Integer initialValue(){
            return random.nextInt(10000);
        }
    };
    public static void increment(){
        value.set(value.get() + 1);
    }
    public static int get(){
        return value.get();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<5;i++){
            executorService.execute(new Accessor(i));
        }
        try {
            TimeUnit.SECONDS.sleep(3);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
