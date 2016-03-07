package threads.SharingResources;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by utsav on 4/2/16.
 */
public class AttemptingLocking {

    private ReentrantLock lock = new ReentrantLock();
    public void untimed(){
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        }finally {
            if (captured)
                lock.unlock();
        }
    }
    public void timed(){
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS) : " + captured);
        }finally {
            if(captured)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        final AttemptingLocking al = new AttemptingLocking();
        al.untimed();
        al.timed();
        new Thread(){
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        //Thread.yield();
        al.untimed();
        al.timed();
    }
}
