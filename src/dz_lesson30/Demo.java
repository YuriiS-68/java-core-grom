package dz_lesson30;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();


        Employee employee1 = new Employee("A1", "AA1", new Date(), Position.TEAM_LEAD);
        Employee employee2 = new Employee("A2", "AA2", new Date(), Position.DEVELOPER);
        Employee employee3 = new Employee("A3", "AA3", new Date(), Position.DEVELOPER);
        Employee employee4 = new Employee("A4", "AA4", new Date(), Position.TEAM_LEAD);
        Employee employee5 = new Employee("A5", "AA5", new Date(), Position.DEVELOPER);
        Employee employee6 = new Employee("A6", "AA6", new Date(), Position.DEVELOPER);
        Employee employee7 = new Employee("A7", "AA7", new Date(), Position.FINANCE);
        Employee employee8 = new Employee("A8", "AA8", new Date(), Position.OTHER);

        Customer customer1 = new Customer("customer1", "China", 1000);
        Customer customer2 = new Customer("customer2", "China", 1200);
        Customer customer3 = new Customer("customer3", "Italy", 800);
        Customer customer4 = new Customer("customer4", "Canada", 1600);

        Projects project1 = new Projects("project1", customer1);
        Projects project2 = new Projects("project2", customer2);
        Projects project3 = new Projects("project3", customer3);

        ArrayList<Projects> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);




    }
}
