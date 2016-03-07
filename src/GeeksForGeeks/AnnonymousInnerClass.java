package GeeksForGeeks;

/**
 * Created by utsav on 16/2/16.
 */

class Demo{
    void show() {
        System.out.println("i am in show method of super class");
    }
}

class FlavourDemo{
    Demo d = new Demo(){
        @Override
        void show() {
            super.show();
            System.out.println("i am in Flavor1Demo class");
        }
    };

    public static void main(String[] args) {
        FlavourDemo flavourDemo = new FlavourDemo();
        flavourDemo.d.show();
    }
}

public class AnnonymousInnerClass {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = new String("abc");

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s2.equals(s3));
        System.out.println(s2 == s3);
        s2 = s3;
        System.out.println(s2 == s3);
    }
}
