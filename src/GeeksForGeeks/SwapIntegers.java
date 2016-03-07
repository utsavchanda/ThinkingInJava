package GeeksForGeeks;

import threads.SharingResources.IntGenerator;

/**
 * Created by utsav on 11/2/16.
 */

/*
* Simply because Integer is non-mutable (immutable).
* Since its immutable, it cannot change its state once created/constructed.
* */

 public class SwapIntegers {

    public static void swap(Integer i, Integer j){
        Integer temp = i;
        i = j;
        j = temp;
    }

    public static void main(String[] args) {
        Integer i = new Integer(10);
        Integer j = new Integer(20);

        System.out.println("i: " + i + " j: " + j);
        swap(i, j);
        System.out.println("i: " + i + " j: " + j);

    }
}

//swap integers
// Test.java
class Test {
    Integer i;


}
class Test2{
    Test test;
    public Test2(Test test){
        this.test=test;
    }
    // swap() doesn't swap i and j
    public static void swap(Test i, Test j) {
        Test temp = new Test();
        temp.i = i.i;
        i.i = j.i;
        j.i = temp.i;
    }
    public static void main(String[] args) {
        Test test1 = new Test();
        Test test2 = new Test();
        test1.i = new Integer(10);
        Test2 test21 = new Test2(test1);
        test2.i = new Integer(20);
        Test2 test22 = new Test2(test2);
        swap(test21.test, test22.test);
        System.out.println("i = " + test21.test.i + ", j = " + test22.test.i);
    }

}

