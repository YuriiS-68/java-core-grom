package dz_lesson28;

import java.util.ArrayList;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception{
        ArrayList<Capability> capabilities = new ArrayList<>();

        Capability capability1 = new Capability(1001, "test", null, false, new Date());
        Thread.sleep(1000);
        Capability capability2 = new Capability(1002, "atest", "1111", false, new Date());
        Thread.sleep(1000);
        Capability capability3 = new Capability(1003, "test", "1111", true, new Date());
        Thread.sleep(1000);
        Capability capability4 = new Capability(1004, "test", "1111", true, new Date());

        capabilities.add(capability3);
        capabilities.add(capability1);
        capabilities.add(capability4);
        capabilities.add(capability2);

        System.out.println(capabilities);

        capabilities.sort(new FullComparator());

        System.out.println(capabilities);
    }
}
