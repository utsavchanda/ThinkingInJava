package threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by utsav on 1/3/16.
 */
public class BlockingQueueLL<T> {

    private List<T> queue = new LinkedList<T>();
    private int limit = 10;
    public BlockingQueueLL(int limit){
        this.limit = limit;
    }

    public synchronized void enqueue(T obj) throws InterruptedException {
        while (this.queue.size() == this.limit){
            wait();
        }
        if (this.queue.size() == 0){
            notifyAll();
        }
        this.queue.add(obj);
    }

    public synchronized T dequeue() throws InterruptedException {
        while (this.queue.size() == 0){
            wait();
        }
        if (this.queue.size() == this.limit)
            notifyAll();
        return this.queue.remove(0);
    }
}
