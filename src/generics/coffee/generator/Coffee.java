package generics.coffee.generator;

/**
 * Created by utsav on 13/2/16.
 */
public class Coffee {

    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}

class Mocha extends Coffee{

}
class Latte extends Coffee{

}
class Cappucino extends Coffee{

}
class Americano extends Coffee{

}
class Breve extends Coffee{

}
