package threads;

/**
 * Created by utsav on 1/2/16.
 */
public class LiftOff implements Runnable {

    protected int countDown = 0;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff(){

    }

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" + (countDown>0?countDown:"threads.LiftOff!") + "), ";
    }

    @Override
    public void run() {

        while (countDown-- > 0){
            System.out.println(status());
            Thread.yield();
        }

    }
}
