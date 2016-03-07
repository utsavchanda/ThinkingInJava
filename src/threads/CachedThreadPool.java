package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by utsav on 1/2/16.
 */
public class CachedThreadPool {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0; i<5; i++){
            executorService.execute(new LiftOff(10));
            //executorService.shutdown();
        }
        executorService.shutdown();
    }
}
