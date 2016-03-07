package threads;

/**
 * Created by utsav on 1/2/16.
 */
public class BasicThreads {
    public static void main(String[] args){
        Thread t = new Thread(new LiftOff(10));
        t.start();
    }
}
