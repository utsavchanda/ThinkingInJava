package Polymorphism.music;

/**
 * Created by utsav on 9/2/16.
 */
public class Music {

    public static void tune(Instrument i){
        i.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);
    }
}
