package lesson10.polyexample;

/**
 * Created by Skorodielov on 15.06.2017.
 */
public class Child extends Human {

    public Child(String name) {
        super(name);
    }

    @Override
    void run() {
        System.out.println("Child class is called...");
        super.run();
    }
    //4
}
