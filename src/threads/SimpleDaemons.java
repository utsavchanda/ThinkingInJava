package threads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 3/2/16.
 */
public class SimpleDaemons implements Runnable {

    @Override
    public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        System.out.println(Thread.currentThread() + " " + this);
                    } catch (InterruptedException e) {
                        System.out.println("sleep interrupted");
                        e.printStackTrace();
                    }
                }
    }

    public static void main(String[] args) throws Exception{
        for(int i = 0; i< 10;i++){
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }

    class DaemonThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }
}
