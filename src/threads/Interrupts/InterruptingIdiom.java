package threads.Interrupts;

import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 8/2/16.
 */

class NeedsCleanup{
    private final int id;
    public NeedsCleanup(int id){
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }
    public void cleanup(){
        System.out.println("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable{
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);

                    NeedsCleanup n2 = new NeedsCleanup(2);

                    try {
                        System.out.println("Calculating");
                        for(int i=0; i<250000; i++){
                            d += (Math.PI + Math.E) / d;
                        }
                        System.out.println("Finished time consuming operation");
                    }finally {
                        n2.cleanup();
                    }
                }finally {
                    n1.cleanup();
                }
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via InterruptedException");
        }
    }
}

public class InterruptingIdiom {

    public static void main(String[] args) throws InterruptedException{
        if(args.length != 1){
            System.out.println("usage: java InterruptingIdiom delay-in-ms");
            System.exit(1);
        }
        Thread thread = new Thread(new Blocked3());
        thread.start();
        TimeUnit.SECONDS.sleep(new Integer(args[0]));
        thread.interrupt();
    }
}
