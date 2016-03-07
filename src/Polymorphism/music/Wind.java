package Polymorphism.music;

/**
 * Created by utsav on 9/2/16.
 */

class Instrument{
    public void play(Note n){
        System.out.println("Instrument.play()");
    }
}

public class Wind extends Instrument {

    @Override
    public void play(Note n) {
        System.out.println("Wind.play() " + n);
    }
}
