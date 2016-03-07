/**
 * Created by utsav on 10/2/16.
 */
public class Singleton {

    private static Singleton singleton;

    private Singleton(){

    }

    public static Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){
                singleton = new Singleton();
            }
        }
        return singleton;
    }

    public void doSomething(){

    }

    public static void main(String[] args) {
        Singleton.getSingleton().doSomething();
    }

}
