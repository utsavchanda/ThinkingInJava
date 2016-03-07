package collections.containers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by utsav on 15/2/16.
 */
public class Maps {

    public static void printKeys(Map<Integer, String> map){
        System.out.print("Size = " + map.size() + ", ");
        System.out.print("Keys: ");
        System.out.println(map.keySet());
    }

    public static void test(Map<Integer, String> map){
        System.out.println(map.getClass().getSimpleName());
        //map.putAll(new );
    }
}
