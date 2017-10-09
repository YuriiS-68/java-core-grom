package dz_lesson30;

import java.util.HashSet;
import java.util.Set;

public class DepartmentDAO {
    private Set<Employee> employees = new HashSet<>();

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
