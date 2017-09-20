package dz_lesson26;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) throws Exception{
        Order order1 = new Order(001, 10, "order1", "itemName1", "w");
        Order order2 = new Order(002, 20, "order2", "itemName2", "d");
        Order order3 = new Order(003, 30, "order3", "itemName3", "s");
        Order order4 = new Order(004, 40, "order4", "itemName4", "m");
        Order order5 = new Order(005, 50, "order5", "itemName5", "l");

        ArrayList<Order> list = new ArrayList<>();
        ArrayListTest arrayListTest = new ArrayListTest();

        arrayListTest.useList(order1, list);
        arrayListTest.useList(order2, list);
        arrayListTest.useList(order3, list);
        arrayListTest.useList(order4, list);
        arrayListTest.useList(order5, list);
        System.out.println(list);



    }
}
