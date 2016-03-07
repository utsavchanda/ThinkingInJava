package strings;

import java.util.Arrays;

/**
 * Created by utsav on 7/3/16.
 */
public class Splitting {

    public static String knights = "Thus when you have found the shrubbery";
    public static void split(String regex){
        System.out.println(Arrays.toString(knights.split(regex)));
    }

    public static void main(String[] args) {
        split(" ");
        split("\\W+");
        split("n\\W+");
    }
}

class Rudolph{
    public static void main(String[] args) {
        for (String pattern : new String[]{
                "Rudolph", "[rR][aeiou][a-z]ol.*", "R.*"
        })
            System.out.println("Rudolph".matches(pattern));
    }
}
