package dz_lesson26;

import java.util.ArrayList;

public class ArrayListTest {

    public ArrayList<Order> useList(Order order, ArrayList list)throws Exception{
        if (order == null)
            throw new Exception("Order " + order.getId() + " does not exist");

        for (int i = 0; i <= 4; i++) {
            list.add(order);
        }
        return list;
    }
}
