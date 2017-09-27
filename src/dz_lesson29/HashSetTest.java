package dz_lesson29;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.util.Arrays.asList;

public class HashSetTest {
    public static void main(String[] args) throws Exception{
        HashSetTest hashSetTest = new HashSetTest();

        Set<Order> set = new HashSet<>();
        Set<Order> set1 = new HashSet<>();

        System.out.println(hashSetTest.useHashSet(orders, orders1, set, set1));

    }

    public static Order order1 = new Order(001, 10, "order1", "itemName1", "1");
    public static Order order2 = new Order(002, 20, "order2", "itemName2", "2");
    public static Order order3 = new Order(003, 30, "order3", "itemName3", "3");
    public static Order order4 = new Order(004, 40, "order4", "itemName4", "4");
    public static Order order5 = new Order(005, 50, "order5", "itemName5", "5");

    public static Order[] orders = {order1, order2, order3, order4, order5};
    public static Order[] orders1 = {order5, order3, order1};

    public Set<Order> useHashSet(Order[] orders, Order[] orders2, Set<Order> setList, Set<Order> setList2)throws Exception{
        if (orders == null || setList == null)
            throw new NullPointerException("Object does not exist");

        setList.addAll(asList(orders));
        setList2.addAll(asList(orders2));

        setList.remove(order1);

        System.out.println(setList.retainAll(setList2));

        Object[] listArray = (Object[]) setList.toArray();
        for (int i = 0; i < listArray.length; i++) {
            System.out.println(listArray[i]);
        }

        System.out.println("Size this is setList - " + setList.size());

        setList.clear();

        setList.addAll(asList(orders));

        Iterator<Order> orderIterator = setList.iterator();
        while (orderIterator.hasNext()){
            System.out.println(orderIterator.next().getItemName());
        }

        return setList;
    }
}
