package concurrency.sharing.resources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by utsav on 19/2/16.
 */
public class EvenChecker implements Runnable {

    private IntGenerator intGenerator;
    private final int id;
    public EvenChecker(IntGenerator intGenerator, int ident){
        this.intGenerator = intGenerator;
        id = ident;
    }
    @Override
    public void run() {
        while (!intGenerator.isCancelled()){
            int val = intGenerator.next();
            if (val%2 == 0){
                System.out.println(val + " not even!");
                intGenerator.cancel();
            }
        }
    }
    public static void test(IntGenerator intGenerator, int count){
        System.out.println("Press COntrol-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<count; i++){
            exec.execute(new EvenChecker(intGenerator, i));
        }
        exec.shutdown();
    }
    public static void test(IntGenerator intGenerator){
        test(intGenerator, 10);
    }
}
