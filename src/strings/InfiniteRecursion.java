package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by utsav on 3/3/16.
 */
public class InfiniteRecursion {

    public String toString(){
        return " Infinite Recursion address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> v = new ArrayList<InfiniteRecursion>();
        for (int i=0;i<10;i++){
            v.add(new InfiniteRecursion());
        }
        System.out.println(v);
    }
}
