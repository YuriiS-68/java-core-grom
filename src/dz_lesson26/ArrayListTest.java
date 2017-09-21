package dz_lesson26;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

public class ArrayListTest {
    public static void main(String[] args)throws Exception {

        ArrayListTest arrayListTest = new ArrayListTest();

        //arrayListTest.useList(orders, list);
        arrayListTest.useList(orders1, list1);

        System.out.println(arrayListTest.useList(orders, list));

    }

    public static Order order1 = new Order(001, 10, "order1", "itemName1", "1");
    public static Order order2 = new Order(002, 20, "order2", "itemName2", "2");
    public static Order order3 = new Order(003, 30, "order3", "itemName3", "3");
    public static Order order4 = new Order(004, 40, "order4", "itemName4", "4");
    public static Order order5 = new Order(005, 50, "order5", "itemName5", "5");

    public static Order[] orders = {order1, order2, order3, order4, order5};
    public static Order[] orders1 = {order5, order3, order1};

    public static ArrayList<Order> list = new ArrayList<>();
    public static ArrayList<Order> list1 = new ArrayList<>();

    public ArrayList<Order> useList(Order[] orders, ArrayList<Order> list)throws Exception{
        if (orders == null || list == null)
            throw new NullPointerException("Object does not exist");

        list.addAll(asList(orders));

        list.add(order2);
        //System.out.println(list);

        list.add(0, order5);
        //System.out.println(list);

        list.remove(2);
        //System.out.println(list);

        list.remove(order1);
        //System.out.println(list);

        list.addAll(list1);
        //System.out.println(list);

        System.out.println(list.subList(1, 4).contains(order2));
        System.out.println(list.subList(1, 4).contains(order1));

        list.subList(1, 4).clear();
        //System.out.println(list);

        list.set(0, order3);
        //System.out.println(list);

        System.out.println(list.contains(order5));

        Object[] listArray = (Object[]) list.toArray();
        for (int i = 0; i < listArray.length; i++) {
            System.out.println(listArray[i]);
        }

        list.clear();
        //System.out.println(list);

        list.addAll(asList(orders));

        return list;
    }
}
