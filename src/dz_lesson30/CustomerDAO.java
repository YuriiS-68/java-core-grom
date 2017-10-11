package dz_lesson30;

import java.util.ArrayList;

public class CustomerDAO {
    private static ArrayList<Customer> customers = new ArrayList<>();

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "CustomerDAO{" +
                "customers=" + customers +
                '}';
    }
}
