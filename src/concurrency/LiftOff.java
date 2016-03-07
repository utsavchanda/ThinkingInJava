package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by utsav on 18/2/16.
 */
public class LiftOff implements Runnable {

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff(){

    }

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" + (countDown>0?countDown:"LiftOff!") + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0){
            System.out.println(status());
            Thread.yield();
        }
    }
}

class BasicThreads{
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("Waiting for LiftOff");
    }
}

class MoreBasicThreads{
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff!");
    }
}

class CachedThreadPool{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0; i<5; i++){
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
