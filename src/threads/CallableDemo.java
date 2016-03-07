package threads;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by utsav on 2/2/16.
 */

class TaskWithResult implements Callable<String>{

    int id;

    public TaskWithResult(int id){
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result " + id;
    }

}

public class CallableDemo {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for(int i=0; i<10; i++){
            results.add(executorService.submit(new TaskWithResult(i)));
        }
        for(Future<String> fs : results){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }
}
