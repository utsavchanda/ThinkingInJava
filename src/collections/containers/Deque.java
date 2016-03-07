package collections.containers;

import java.util.LinkedList;

/**
 * Created by utsav on 15/2/16.
 */
public class Deque<T> {

    private LinkedList<T> deque = new LinkedList<T>();
    public void addFirst(T e){
        deque.addFirst(e);
    }
    public void addLast(T e){
        deque.addLast(e);
    }
    public T getFirst(){
        return deque.getFirst();
    }
    public T getLast(){
        return deque.getLast();
    }
    public T removeFirst(){
        return deque.removeFirst();
    }
    public T removeLast(){
        return removeLast();
    }
    public int size(){
        return deque.size();
    }

    @Override
    public String toString() {
        return deque.toString();
    }
}
