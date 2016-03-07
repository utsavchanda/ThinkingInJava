package threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by utsav on 5/2/16.
 */

class Pair{
    private int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Pair(){
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public void incrementX(){
        x++;
    }

    public void incrementY(){
        y++;
    }

    @Override
    public String toString() {
        return "x: " + x + "y: " + y;
    }

    public int getY() {
        return y;
    }

    public class PairValuesNotEqualException extends RuntimeException{
        public PairValuesNotEqualException(){
            super("Pair values not equal: " + Pair.this);
        }
    }

    public void checkState(){
        if(x != y){
            throw new PairValuesNotEqualException();
        }
    }

}

abstract class PairManager{
    AtomicInteger atomicInteger = new AtomicInteger(0);
    protected Pair pair = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
    public synchronized Pair getPair(){
        return new Pair(pair.getX(), pair.getY());
    }
    protected void store(Pair p){
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public abstract void increment();
}
class PairManager1 extends PairManager{
    public synchronized void increment(){
        pair.incrementX();
        pair.incrementY();
        store(pair);
    }
}

class PairManager2 extends PairManager{

    @Override
    public void increment() {
        Pair temp;
        synchronized (this){
            pair.incrementY();
            pair.incrementY();
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

    @Override
    public String toString() {
        return "Pair: " + pairManager.getPair() + " check";
    }
}

class PairChecker implements Runnable{
    private PairManager pm;
    public PairChecker(PairManager pm){
        this.pm = pm;
    }
    @Override
    public void run() {
        while (true){
            pm.atomicInteger.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

public class CriticalSection {

    static void testApproaches(PairManager pairManager1, PairManager pairManager2){

        ExecutorService executorService = Executors.newCachedThreadPool();
        PairManipulator pairManipulator1 = new PairManipulator(pairManager1);
        PairManipulator pairManipulator2 = new PairManipulator(pairManager2);

        PairChecker pairChecker1 = new PairChecker(pairManager1);
        PairChecker pairChecker2 = new PairChecker(pairManager2);

        executorService.execute(pairManipulator1);
        executorService.execute(pairManipulator2);

        executorService.execute(pairChecker1);
        executorService.execute(pairChecker2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e){
            System.out.println("sleep interrupted");
        }
        System.out.println("pm1: " + pairManager1 + " pm2: " + pairManager2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pairManager1 = new PairManager1();
        PairManager pairManager2 = new PairManager2();
        testApproaches(pairManager1, pairManager2);
    }

}
