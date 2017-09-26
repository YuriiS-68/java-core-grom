package lesson28;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class DemoComparable {
    public static void main(String[] args) {
        Capability capability1 = new Capability(1001, "test", "rrrr", true, new Date());
        Capability capability2 = new Capability(1005, "test", "rrrr", true, new Date());
        Capability capability3 = new Capability(900, "test", "rrrr", true, new Date());

        Set<Capability> set = new TreeSet<>();
        set.add(capability1);
        set.add(capability2);
        set.add(capability3);

        System.out.println(set);
    }
}
