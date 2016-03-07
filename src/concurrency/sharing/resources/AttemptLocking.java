package concurrency.sharing.resources;

import threads.SharingResources.AttemptingLocking;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by utsav on 25/2/16.
 */
public class AttemptLocking {

    private ReentrantLock lock = new ReentrantLock();
    public void untimed(){
        boolean captured = lock.tryLock();
        try {
            System.out.println("trylock: " + captured);
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
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        }finally {
            if (captured)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        final AttemptLocking attemptingLocking = new AttemptLocking();
        attemptingLocking.untimed();
        attemptingLocking.timed();

        new Thread(){
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                attemptingLocking.lock.lock();
                System.out.println("acquired");
            }
        }.start();

        Thread.yield();
        attemptingLocking.untimed();
        attemptingLocking.timed();
    }
}
