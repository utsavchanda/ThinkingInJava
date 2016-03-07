package GeeksForGeeks;

/**
 * Created by utsav on 11/2/16.
 */

class Car{
    int model, no;

    Car(int model, int no) {
        this.model = model;
        this.no = no;
    }

    void print(){
        System.out.println("no = " + no + " model= " + model);
    }
}

class CarWrapper{
    Car car;

    CarWrapper(Car car) {
        this.car = car;
    }
}

public class SwapObjects {

    public static void swap(CarWrapper carWrapper1, CarWrapper carWrapper2){
        Car temp = carWrapper1.car;
        carWrapper1.car = carWrapper2.car;
        carWrapper2.car = temp;
    }

    public static void main(String[] args) {

        Car c1 = new Car(101, 1);
        Car c2 = new Car(202, 2);

        CarWrapper carWrapper1 = new CarWrapper(c1);
        CarWrapper carWrapper2 = new CarWrapper(c2);

        swap(carWrapper1, carWrapper2);

        
    }

}


