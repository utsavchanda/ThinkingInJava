package threads;

/**
 * Created by utsav on 1/2/16.
 */
public class MainThread {

    public static void main(String[] args){
        LiftOff liftOff = new LiftOff(10);
        liftOff.run();
    }
}
