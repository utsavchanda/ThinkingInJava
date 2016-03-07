package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by utsav on 16/2/16.
 */
class Count{
    private int count=0;
    private Random random = new Random(47);

    public synchronized int increment(){
        int temp = count;
        if(random.nextBoolean())
            Thread.yield();
        return (count=++temp);
    }

    public synchronized int value(){
        return count;
    }
}
class Entrance implements Runnable{
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private int number = 0;
    private final int id;
    private static volatile boolean canceled = false;

    public static void cancel(){
        canceled=true;
    }
    public Entrance(int id){
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run() {

    }
}
public class OrnamentalGarden {
}














