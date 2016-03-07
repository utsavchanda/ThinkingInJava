package concurrency.sharing.resources.critical.sections;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by utsav on 25/2/16.
 */

class Pair{
    private int x,y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Pair(){
        this(0, 0);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void incrementX(){
        x++;
    }
    public void incrementY(){
        y++;
    }
    public String toString(){
        return "x: " + x + "y: " + y;
    }

    public class PairValuesNotEqualException extends RuntimeException{
        public PairValuesNotEqualException(){
            super("Pair values not equal: " + Pair.this);
        }
    }

    public void checkState(){
        if (x != y){
            throw new PairValuesNotEqualException();
        }
    }
}
abstract class PairManager{
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized Pair getPair(){
        return new Pair(p.getX(), p.getY());
    }

    protected void store(Pair p){
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        }catch (InterruptedException e){

        }
    }
    public abstract void increment();
}

class PairManager1 extends PairManager{
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

class PairManager2 extends PairManager{
    @Override
    public void increment() {
        Pair temp;
        synchronized (this){
            p.incrementY();
            p.incrementX();
            temp = getPair();
        }
        store(temp);
    }


}

class PairManipulator implements Runnable{
    private PairManager pairManager;
    public PairManipulator(PairManager pairManager){
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true){
            pairManager.increment();
        }
    }

    public String toString(){
        return "Pair: " + pairManager.getPair() + " checkCounter= " + pairManager.checkCounter.get();
    }
}

class PairChecker implements Runnable{
    private PairManager pairManager;
    public PairChecker(PairManager pairManager){
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true){
            pairManager.checkCounter.incrementAndGet();
            pairManager.getPair().checkState();
        }
    }
}

public class CriticalSection {

    static void testApproaches(PairManager pairManager1, PairManager pairManager2){
        ExecutorService executorService = Executors.newCachedThreadPool();
        PairManipulator
                pairManipulator1 = new PairManipulator(pairManager1),
                pairManipulator2 = new PairManipulator(pairManager2);
        PairChecker
                pairChecker1 = new PairChecker(pairManager1),
                pairChecker2 = new PairChecker(pairManager2);

        executorService.execute(pairManipulator1);
        executorService.execute(pairManipulator2);
        executorService.execute(pairChecker1);
        executorService.execute(pairChecker2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e){
            System.out.println("Sleep interrupted");
        }
        System.out.println("pm1: " + pairManipulator1 + " \npm2: " + pairManipulator2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager
                pairManager1 = new PairManager1(),
                pairManager2 = new PairManager2();
        testApproaches(pairManager1, pairManager2);

    }
}

class ExplicitPairManager1 extends PairManager{
    private Lock lock = new ReentrantLock();

    @Override
    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(p);
        }finally {
            lock.unlock();
        }
    }
}

class ExplicitPairManager2 extends PairManager{
    private Lock lock = new ReentrantLock();

    @Override
    public synchronized void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }finally {
            lock.unlock();
        }
        store(temp);
    }
}

class ExplicitCriticalSection{
    public static void main(String[] args) {
        PairManager
                pairManager1 = new PairManager1(),
                pairManager2 = new PairManager2();
        CriticalSection.testApproaches(pairManager1, pairManager2);
    }
}
