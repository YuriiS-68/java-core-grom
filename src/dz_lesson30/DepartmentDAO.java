package dz_lesson30;

import java.util.LinkedList;

public class DepartmentDAO {
    private static LinkedList<Department> departments = new LinkedList<>();

    public static void setDepartments(LinkedList<Department> departments) {
        DepartmentDAO.departments = departments;
    }
}
