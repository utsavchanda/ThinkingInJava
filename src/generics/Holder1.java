package generics;

/**
 * Created by utsav on 13/2/16.
 */

class Automobile{

}

public class Holder1 {
    private Automobile a;
    public Holder1(Automobile a){
        this.a = a;
    }
    Automobile get(){
        return a;
    }
}

class Holder2 {
    private Object a;
    public Holder2(Object a){
        this.a = a;
    }
    public void set(Object a){
        this.a = a;
    }
    public Object get(){
        return a;
    }
}

class Holder3<T> {
    private T a;
    public Holder3(T a){
        this.a = a;
    }
    public void set(T a){
        this.a = a;
    }
    public T get(){
        return a;
    }
}

class TwoTuple<A,B>{
    public final A a;
    public final B b;
    public TwoTuple(A a, B b){
        this.a = a;
        this.b = b;
    }
    public String toString(){
        return "(" + a + ", " + b + ")";
    }
}

class ThreeTuple<A,B,C> extends TwoTuple<A,B>{
    public final C c;
    public ThreeTuple(A a, B b, C c){
        super(a, b);
        this.c = c;
    }
}

class Amphibian{}
class Vehicle{}

class TupleTest{
    static TwoTuple<String, Integer> f(){
        return new TwoTuple<String, Integer>("hi", 47);
    }
    static ThreeTuple<Amphibian, String, Integer> g(){
        return new ThreeTuple<Amphibian, String, Integer>(new Amphibian(), "hi", 47);
    }
}