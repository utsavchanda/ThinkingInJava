package concurrency.sharing.resources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by utsav on 25/2/16.
 */
public class MutexEvenGenerator extends IntGenerator {

    private int currentIntValue = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public int next() {

        lock.lock();
        try{
            ++currentIntValue;
            Thread.yield();
            ++currentIntValue;
            return currentIntValue;
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
