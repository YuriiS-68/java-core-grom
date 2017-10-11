package dz_lesson30;

import java.util.ArrayList;

public class EmployeeDAO {
    private static ArrayList<Employee> employees = new ArrayList<>();

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        EmployeeDAO.employees = employees;
    }

    @Override
    public String toString() {
        return "EmployeeDAO{" +
                "employees=" + employees +
                '}';
    }
}
