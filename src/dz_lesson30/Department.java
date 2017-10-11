package dz_lesson30;

import java.util.Collection;

public class Department {
    private DepartmentType type;
    private Collection<Employee> employees;

    public Department(DepartmentType type, Collection<Employee> employees) {
        this.type = type;
        this.employees = employees;
    }

    public DepartmentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Department{" +
                "type=" + type +
                '}';
    }
}
