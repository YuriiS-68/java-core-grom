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

    public static ArrayList<Employee> employeesByTeamLead(Employee lead){


        return null;
    }

}
