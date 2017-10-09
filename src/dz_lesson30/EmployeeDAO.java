package dz_lesson30;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class EmployeeDAO {
    private Set<Employee> employees = new HashSet<>();

    public Set<Employee> getEmployees() {
        return employees;
    }
}
