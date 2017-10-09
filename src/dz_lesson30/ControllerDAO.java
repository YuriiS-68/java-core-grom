package dz_lesson30;

import java.util.Collection;
import java.util.LinkedList;

public class ControllerDAO {

    private Collection<Projects> projects = new LinkedList<>();

    EmployeeDAO employeeDAO = new EmployeeDAO();

    public Collection<Employee> employeesWorkingProject(Projects projects)throws NullPointerException{
        //пройти по списку сотрудников и взять только тех , которые заняты в данном проекте
        if (projects == null)
            throw new NullPointerException("Object does not exist");






        return null;
    }

    public Collection<Projects> listProjectsWhichEmployeeWorks(Employee employee){


        return null;
    }


}
