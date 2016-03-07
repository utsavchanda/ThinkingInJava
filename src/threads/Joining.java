package threads;

/**
 * Created by utsav on 4/2/16.
 */

class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name, int sleepTime){
        super(name);
        duration = sleepTime;
        start();
    }
    public void run(){
        try {
            sleep(duration);
        }catch (InterruptedException e){
            System.out.println(getName() + " was interrupted. " + " isInterrupted: " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }
    public void run(){
        try{
            sleeper.join();
        }catch (InterruptedException e){
            System.out.println(getName() + " join completed");
        }
    }
}

public class Joining {

    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("Sleepy", 3000);
        Sleeper grumpy = new Sleeper("Grumpy", 3000);

        Joiner dopey = new Joiner("dopey", sleepy);
        Joiner doc = new Joiner("doc", grumpy);

        grumpy.interrupt();

    }
}
