package dz_lesson26;

import java.util.ArrayList;

public class ArrayListTest {

    ArrayList<Order> list = new ArrayList<>();

    public ArrayList<Order> useList(Order order, ArrayList list)throws Exception{
        if (order == null)
            throw new Exception("Order " + order.getId() + " does not exist");

        list.add(order);

        return list;
    }
}
