package threads.WaitAndNotifyAll;

import com.sun.javaws.exceptions.ExitException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 8/2/16.
 */

class Car{
    private boolean waxOn = false;
    public synchronized void waxed(){
        waxOn = true;
        notifyAll();
    }
    public synchronized void buffed(){
        waxOn = false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException{
        while (waxOn == false){
            wait();
        }
    }
    public synchronized void waitForBuffing() throws InterruptedException{
        while (waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable{
    private Car car;
    public WaxOn(Car car){
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.println("Wax on! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting Wax on task");
        }
        System.out.println("Ending Wax on task");
    }
}

class WaxOff implements Runnable{
    private Car car;
    public WaxOff(Car car){
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                car.waitForWaxing();
                System.out.println("War off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff(car));
        executorService.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
