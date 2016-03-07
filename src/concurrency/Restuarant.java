package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 1/3/16.
 */

class Meal{
    private final int orderNum;
    public Meal(int orderNum){
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable{
    private Restuarant restuarant;
    public WaitPerson(Restuarant r){
        restuarant = r;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            synchronized (this){
                while (restuarant.meal == null){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Waitperson interrupted");
                    }
                }
            }
            System.out.println("Waitperson got " + restuarant.meal);
            synchronized (restuarant.chef){
                restuarant.meal = null;
                restuarant.chef.notifyAll();
            }
        }
    }
}

class Chef implements Runnable{
    private Restuarant restuarant;
    private int count = 0;

    public Chef(Restuarant r){
        restuarant = r;
    }
    @Override
    public void run() {
            while (!Thread.interrupted()){
                synchronized (this){
                    while (restuarant.meal == null){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (++count == 10){
                        System.out.println("out of food, closing");
                        restuarant.exec.shutdown();
                    }
                    System.out.print("order up!");
                    synchronized (restuarant.waitPerson){
                        restuarant.meal = new Meal(count);
                        restuarant.waitPerson.notifyAll();
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
public class Restuarant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);

    public Restuarant(){
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.shutdown();
    }

    public static void main(String[] args) {
        new Restuarant();
    }

}
