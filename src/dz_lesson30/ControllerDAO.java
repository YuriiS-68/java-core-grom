package dz_lesson30;

import dz_lesson30.exception.BadRequestException;

import java.util.ArrayList;

public class ControllerDAO {

    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static ProjectDAO projectDAO = new ProjectDAO();

    public static ArrayList<Employee> employeesByProject(Project project)throws NullPointerException{
        //пройти по списку сотрудников и взять только тех , которые заняты в данном проекте
        if (project == null)
            throw new NullPointerException("Project does not exist");

        ArrayList<Employee> employeesOnProject = new ArrayList<>();

        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getProjects() != null && employee.getProjects().contains(project)){
                employeesOnProject.add(employee);
            }
        }
        return employeesOnProject;
    }

    public static ArrayList<Project> projectsByEmployee(Employee employee)throws Exception{
        if (employee == null)
            throw new NullPointerException("Employee does not exist");

        for (Employee employee1 : employeeDAO.getEmployees()){
            if (employee1 != null && employee1.equals(employee)){
                return (ArrayList<Project>) employee.getProjects();
            }
        }
        throw new BadRequestException("Employee not was found");
    }

    public static ArrayList<Employee> employeesByDepartmentWithoutProject(Department department)throws Exception{
        if (department == null)
            throw new NullPointerException("Department does not exist");

        ArrayList<Employee> employeesWithoutProject = new ArrayList<>();
        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getProjects() == null && employee.getDepartment().equals(department)){
                employeesWithoutProject.add(employee);
            }
        }
        return employeesWithoutProject;
    }

    public static ArrayList<Employee> employeesWithoutProject(){
        ArrayList<Employee> employeesWithoutProject = new ArrayList<>();
        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getProjects() == null){
                employeesWithoutProject.add(employee);
            }
        }
        return employeesWithoutProject;
    }

    public static ArrayList<Employee> employeesByTeamLead(Employee lead)throws Exception{
        //пройти по списку сотрудников и сравнить проекты, в которых они задействованы и сравнить департамент, в которм они находятся
        //вернуть список или вывести его в консоль
        if (lead == null)
            throw new NullPointerException("Lead does not exist");

        if (lead.getPosition() != Position.TEAM_LEAD)
            throw new BadRequestException("You did not enter the manager");

        ArrayList<Employee> employeesByTeamLead = new ArrayList<>();
        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && lead.getDepartment() != null && lead.getProjects() != null && employee.getDepartment() != null ){
                if (employee.getProjects() != null && employee.getProjects().equals(lead.getProjects()) && employee.getDepartment().equals(lead.getDepartment())){
                    if (employee.getPosition() != Position.TEAM_LEAD){
                        employeesByTeamLead.add(employee);
                    }
                }
            }
        }
        return employeesByTeamLead;
    }

    public static ArrayList<Employee> teamLeadsByEmployee(Employee employee)throws Exception{
        if (employee == null)
            throw new NullPointerException("Employee does not exist");

        if (employee.getPosition() == Position.TEAM_LEAD)
            throw new BadRequestException("You did not enter the manager");

        ArrayList<Employee> teamLeadsByEmployee = new ArrayList<>();
        for (Employee employee1 : employeeDAO.getEmployees()){
            if (employee1 != null && employee1.getProjects() != null && employee1.getPosition() == Position.TEAM_LEAD){
                if (employee.getProjects() != null && employee.getProjects().equals(employee1.getProjects())){
                    teamLeadsByEmployee.add(employee1);
                }
            }
        }
        return teamLeadsByEmployee;
    }

    public static ArrayList<Employee> employeesByProjectEmployee(Employee employee)throws Exception{
        if (employee == null)
            throw new NullPointerException("Employee does not exist");

        if (employee.getPosition() == Position.TEAM_LEAD)
            throw new BadRequestException("You did not enter an employee");

        ArrayList<Employee> employeesByProjectEmployee = new ArrayList<>();
        for (Employee employee1 : employeeDAO.getEmployees()){
            if (employee1 != null && employee.getProjects() != null && employee1.getProjects() != null && employee.getPosition() != Position.TEAM_LEAD){
                if (employee1.getPosition() != Position.TEAM_LEAD && employee1.getProjects().equals(employee.getProjects())){
                    employeesByProjectEmployee.add(employee1);
                }
            }
        }
        return employeesByProjectEmployee;
    }

    public static ArrayList<Project> projectsByCustomer(Customer customer)throws Exception{
        if (customer == null)
            throw new NullPointerException("Customer does not exist");

        ArrayList<Project> projectsByCustomer = new ArrayList<>();
        for (Project project : projectDAO.getProjects()){
            if (project != null && project.getCustomer().equals(customer)){
                projectsByCustomer.add(project);
            }
        }
        return projectsByCustomer;
    }

    public static ArrayList<Employee> employeesByCustomerProjects(Customer customer)throws Exception{
        if (customer == null)
            throw new NullPointerException("Customer does not exist");

        ArrayList<Employee> employeesByCustomerProjects = new ArrayList<>();
        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getProjects() != null && employee.getProjects().contains(customer)){
                employeesByCustomerProjects.add(employee);
            }
        }
        return employeesByCustomerProjects;
    }

}