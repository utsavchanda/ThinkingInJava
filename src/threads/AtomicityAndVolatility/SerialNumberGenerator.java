package threads.AtomicityAndVolatility;

/**
 * Created by utsav on 5/2/16.
 */
public class SerialNumberGenerator {

    private volatile static int serialNumber = 0;
    public static /*synchronized*/ int nextSerialNumber(){

        return serialNumber++;
    }

}
