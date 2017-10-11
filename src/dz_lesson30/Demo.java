package dz_lesson30;

import java.util.Date;

public class Demo {
    public static void main(String[] args)throws Exception{

        EmployeeDAO employeeDAO = new EmployeeDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();

        Employee employee1 = new Employee("A1", "AA1", new Date(), Position.TEAM_LEAD);
        Employee employee2 = new Employee("A2", "AA2", new Date(), Position.DEVELOPER);
        Employee employee3 = new Employee("A3", "AA3", new Date(), Position.DEVELOPER);
        Employee lead = new Employee("LEAD", "TL1", new Date(), Position.TEAM_LEAD);
        Employee employee5 = new Employee("A5", "AA5", new Date(), Position.DEVELOPER);
        Employee employee6 = new Employee("A6", "AA6", new Date(), Position.DEVELOPER);
        Employee employee7 = new Employee("A7", "AA7", new Date(), Position.FINANCE);
        Employee employee8 = new Employee("A8", "AA8", new Date(), Position.OTHER);

        //просетить руководителю департамент
        //просетить руководителю список проектов, которые он ведёт
        //пройти по списку сотрудников и сравнить проекты, в которых они задействованы и сравнить департамент, в которм они находятся
        //вернуть список или вывести его в консоль


        Department department1 = new Department(DepartmentType.ECOLOGICAL, employeeDAO.getEmployees());
        Department department2 = new Department(DepartmentType.SCIENTIFIC, employeeDAO.getEmployees());
        lead.setDepartment(department1);
        employee1.setDepartment(department1);
        employee2.setDepartment(department1);
        employee3.setDepartment(department2);
        employee5.setDepartment(department1);
        employee6.setDepartment(department1);

        Customer customer1 = new Customer("customer1", "China", 1000);
        Customer customer2 = new Customer("customer2", "China", 1200);
        Customer customer3 = new Customer("customer3", "Italy", 800);
        Customer customer4 = new Customer("customer4", "Canada", 1600);

        customerDAO.getCustomers().add(customer1);
        customerDAO.getCustomers().add(customer2);
        customerDAO.getCustomers().add(customer3);
        customerDAO.getCustomers().add(customer4);

        Project project1 = new Project("project1", customer1);
        Project project2 = new Project("project2", customer1);
        Project project3 = new Project("project3", customer3);

        projectDAO.getProjects().add(project1);
        projectDAO.getProjects().add(project2);

        lead.setProjects(projectDAO.getProjects());
        employee1.setProjects(projectDAO.getProjects());
        employee5.setProjects(projectDAO.getProjects());
        employee3.setProjects(projectDAO.getProjects());

        employeeDAO.getEmployees().add(employee3);
        employeeDAO.getEmployees().add(employee1);
        employeeDAO.getEmployees().add(employee5);
        employeeDAO.getEmployees().add(employee2);
        employeeDAO.getEmployees().add(employee6);

        System.out.println(employeeDAO.getEmployees());

        System.out.println();
        System.out.println("Array employees: - " + employeeDAO.getEmployees());
        System.out.println();
        System.out.println("Employees working on the project1: - " + ControllerDAO.employeesByProject(project1));
        System.out.println("Employees working on the project2: - " + ControllerDAO.employeesByProject(project2));

        System.out.println(ControllerDAO.projectsByEmployee(employee1));
        System.out.println(ControllerDAO.projectsByEmployee(employee5));
        System.out.println();
        System.out.println(ControllerDAO.employeesByDepartmentWithoutProject(department2));
        System.out.println();
        System.out.println(ControllerDAO.employeesWithoutProject());
        System.out.println();
        System.out.println(ControllerDAO.employeesByTeamLead(lead));
        System.out.println();
        System.out.println(ControllerDAO.teamLeadsByEmployee(employee3));
        System.out.println();
        System.out.println(ControllerDAO.employeesByProjectEmployee(employee5));
        System.out.println();
        System.out.println(ControllerDAO.projectsByCustomer(customer1));
        System.out.println();
        System.out.println(ControllerDAO.employeesByCustomerProjects(customer1));
    }
}
