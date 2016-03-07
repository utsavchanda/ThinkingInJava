package concurrency.sharing.resources;

/**
 * Created by utsav on 4/2/16.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();

    public void cancel(){
        canceled = true;
    }

    public boolean isCancelled(){
        return canceled;
    }
}
