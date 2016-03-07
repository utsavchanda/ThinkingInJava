package concurrency.sharing.resources;

/**
 * Created by utsav on 25/2/16.
 */
public class SynchronizedEvenGenerator extends IntGenerator{

    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
