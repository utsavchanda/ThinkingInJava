package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by utsav on 13/2/16.
 */
public class RandomList<T> {
    private List<T> storage = new ArrayList<T>();
    private Random random = new Random(47);
    public void add(T item){
        storage.add(item);
    }
    public T select(){
        return storage.get(random.nextInt(storage.size()));
    }

    public static void main(String[] args) {
        RandomList<String> stringRandomList = new RandomList<String>();
        for(String s: ("The quick brown fox jumped over the lazy brown dog").split(" "))
            stringRandomList.add(s);
        for (int i=0; i<11; i++){
            System.out.println(stringRandomList.select());
        }
    }
}
