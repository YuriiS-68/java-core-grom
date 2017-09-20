package dz_lesson26;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) throws Exception{
        Order order1 = new Order(001, 10, "order1", "itemName1", "1");
        Order order2 = new Order(002, 20, "order2", "itemName2", "2");
        Order order3 = new Order(003, 30, "order3", "itemName3", "3");
        Order order4 = new Order(004, 40, "order4", "itemName4", "4");
        Order order5 = new Order(005, 50, "order5", "itemName5", "5");

        ArrayList<Order> list = new ArrayList<>();
        ArrayList<Order> list1 = new ArrayList<>();
        ArrayListTest arrayListTest = new ArrayListTest();

        arrayListTest.useList(order1, list);
        arrayListTest.useList(order4, list1);
        System.out.println(list);

        list.add(order2);
        System.out.println(list);

        list.add(0, order5);
        System.out.println(list);

        list.remove(2);
        System.out.println(list);

        list.remove(order1);
        System.out.println(list);

        list.addAll(list1);
        System.out.println(list);

        System.out.println(list.subList(1, 4).contains(order2));
        System.out.println(list.subList(1, 4).contains(order1));

        list.subList(1, 4).clear();
        System.out.println(list);

        list.set(0, order3);
        System.out.println(list);

        System.out.println(list.contains(order5));

        Object[] listArray = (Object[]) list.toArray();
        for (int i = 0; i < listArray.length; i++) {
            System.out.println(listArray[i]);
        }

        list.clear();
        System.out.println(list);

    }
}
