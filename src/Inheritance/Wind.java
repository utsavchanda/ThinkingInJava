package Inheritance;

/**
 * Created by utsav on 9/2/16.
 */

class Instrument {

    protected int i;
    protected static void play(){
        System.out.println("from base");
    }
    static void tune(Instrument instrument){
        instrument.play();
    }
}

public class Wind extends Instrument{

    //@Override
    public static void play() {
        System.out.println("from derived");
    }

    public static void main(String[] args) {
        //Instrument.tune(new Wind());
        Wind instrument = new Wind();
        instrument.play();


    }

}
