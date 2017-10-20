package dz_lesson30;

import dz_lesson30.exception.BadRequestException;
import dz_lesson30.exception.InternalServerException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ControllerDAO {

    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static ProjectDAO projectDAO = new ProjectDAO();

    public static ArrayList<Employee> employeesByProject(Project project)throws Exception{
        //пройти по списку сотрудников и взять только тех , которые заняты в данном проекте
        if (project == null)
            throw new Exception("Project does not exist");

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
            throw new Exception("Employee does not exist");

        for (Employee employee1 : employeeDAO.getEmployees()){
            if (employee1 != null && employee1.equals(employee)){
                return (ArrayList<Project>) employee.getProjects();
            }
        }
        throw new BadRequestException("Employee not was found");
    }

    public static ArrayList<Employee> employeesByDepartmentWithoutProject(Department department)throws Exception{
        //взять сотрудников из нужного департамерта
        //проверить есть ли список проектов у сотрудника, если нет или если список равен нулю заносить этого сотрудника в список
        if (department == null)
            throw new Exception("Department does not exist");

        ArrayList<Employee> employeesWithoutProject = new ArrayList<>();
        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getDepartment().equals(department) && employee.getProjects() != null && employee.getProjects().size() == 0){
                employeesWithoutProject.add(employee);
            }
        }

        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getDepartment().equals(department) && employee.getProjects() == null){
                employeesWithoutProject.add(employee);
            }
        }
        return employeesWithoutProject;
    }

    public static ArrayList<Employee> employeesWithoutProject(){
        ArrayList<Employee> employeesWithoutProject = new ArrayList<>();
        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getProjects() != null && employee.getProjects().size() == 0){
                employeesWithoutProject.add(employee);
            }
        }

        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getProjects() == null){
                employeesWithoutProject.add(employee);
            }
        }
        return employeesWithoutProject;
    }

    public static Set<Employee> employeesByTeamLead(Employee lead)throws Exception{
        //пройти по списку проектов руководителя и найти его участников
        //вернуть список сотрудников у которых будет совпадать проект с проектом руководителя
        if (lead == null)
            throw new Exception("Lead does not exist");

        if (lead.getPosition() != Position.TEAM_LEAD)
            throw new BadRequestException("You did not enter the manager");

        Set<Employee> employeesByTeamLead = new HashSet<>();
        for (Project project : projectsByForLead(lead)){
            if (project != null){
                employeesByTeamLead.addAll(onlyEmployeesByProject(project));
            }
        }
        return employeesByTeamLead;
    }

    public static Set<Employee> teamLeadsByEmployee(Employee employee)throws Exception{
        //пройти по списку проектов сотрудника и найти его руководителей
        //вернуть список сотрудников у которых будет совпадать проект с проектом руководителя
        if (employee == null)
            throw new Exception("Employee does not exist");

        if (employee.getPosition() == Position.TEAM_LEAD)
            throw new BadRequestException("You did not enter the employee");

        Set<Employee> teamLeadsByEmployee = new HashSet<>();
        for (Project project : projectsByForEmployee(employee)){
            if(project != null){
                teamLeadsByEmployee.addAll(onlyLeadByProject(project));
            }
        }
        return teamLeadsByEmployee;
    }

    public static Set<Employee> employeesByProjectEmployee(Employee employee)throws Exception{
        //взять список проектов сотрудника
        //пройти по нему и взять сотрудников с такими же проектами
        if (employee == null)
            throw new Exception("Employee does not exist");

        if (employee.getPosition() == Position.TEAM_LEAD)
            throw new BadRequestException("You did not enter an employee");

        Set<Employee> employeesByProjectEmployee = new HashSet<>();
        for (Project project : projectsByForEmployee(employee)){
            if (project != null){
                employeesByProjectEmployee.addAll(employeesByProject(project));
            }
        }

        employeesByProjectEmployee.remove(employee);

        return employeesByProjectEmployee;
    }

    public static ArrayList<Project> projectsByCustomer(Customer customer)throws Exception{
        if (customer == null)
            throw new Exception("Customer does not exist");

        ArrayList<Project> projectsByCustomer = new ArrayList<>();
        for (Project project : projectDAO.getProjects1()){
            if (project != null && project.getCustomer().equals(customer)){
                projectsByCustomer.add(project);
            }
        }
        return projectsByCustomer;
    }

    public static Set<Employee> employeesByCustomerProjects(Customer customer)throws Exception{
        if (customer == null)
            throw new Exception("Customer does not exist");

        Set<Employee> employeesByCustomerProjects = new HashSet<>();
        for (Project project : projectsByCustomer(customer)){
            if (project != null){
                employeesByCustomerProjects.addAll(employeesByProject(project));
            }
        }
        return employeesByCustomerProjects;
    }

    private static ArrayList<Employee> onlyEmployeesByProject(Project project)throws Exception{
        //пройти по списку сотрудников и взять только тех , которые заняты в данном проекте
        if (project == null)
            throw new Exception("Project does not exist");

        ArrayList<Employee> employeesOnProject = new ArrayList<>();
        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getPosition() != Position.TEAM_LEAD && employee.getProjects() != null && employee.getProjects().contains(project)){
                employeesOnProject.add(employee);
            }
        }
        return employeesOnProject;
    }

    private static ArrayList<Project> projectsByForLead(Employee employee)throws Exception{
        if (employee == null)
            throw new Exception("Employee does not exist");

        for (Employee employee1 : employeeDAO.getEmployees()){
            if (employee1 != null && employee.getPosition() == Position.TEAM_LEAD && employee1.equals(employee)){
                return (ArrayList<Project>) employee.getProjects();
            }
        }
        throw new BadRequestException("Employee not was found");
    }

    private static ArrayList<Project> projectsByForEmployee(Employee employee)throws Exception{
        if (employee == null)
            throw new Exception("Employee does not exist");

        for (Employee employee1 : employeeDAO.getEmployees()){
            if (employee1 != null && employee.getPosition() != Position.TEAM_LEAD && employee1.equals(employee)){
                return (ArrayList<Project>) employee.getProjects();
            }
        }
        throw new BadRequestException("Employee not was found");
    }

    private static ArrayList<Employee> onlyLeadByProject(Project project)throws Exception{
        //пройти по списку сотрудников и взять только тех руководителей, в проекта которых занят данный сотрудник
        if (project == null)
            throw new Exception("Project does not exist");

        ArrayList<Employee> employeesOnProject = new ArrayList<>();
        for (Employee employee : employeeDAO.getEmployees()){
            if (employee != null && employee.getPosition() == Position.TEAM_LEAD && employee.getProjects() != null && employee.getProjects().contains(project)){
                employeesOnProject.add(employee);
            }
        }
        return employeesOnProject;
    }
}
