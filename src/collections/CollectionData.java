package collections;

import generics.Generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by utsav on 14/2/16.
 */
public class CollectionData<T> extends ArrayList<T> {

    public CollectionData(Generator<T> gen, int quantity){
        for (int i=0; i<quantity; i++){
            add(gen.next());
        }
    }

    public static <T> CollectionData<T> list(Generator<T> gen, int quantity){
        return new CollectionData<T>(gen, quantity);
    }
}

class Government implements Generator<String>{

    String[] foundation = ("strange men lying").split(" ");
    private int index;

    @Override
    public String next() {
        return foundation[index++];
    }
}

class CollectionDataSet{
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>(new CollectionData<String>(new Government(), 3));
        set.addAll(CollectionData.list(new Government(), 3));
        System.out.println(set);
    }
}
