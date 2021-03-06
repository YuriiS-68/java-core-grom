package dz_lesson30;

import java.util.ArrayList;

public class ProjectDAO {
    private static ArrayList<Project> projects1 = new ArrayList<>();
    private static ArrayList<Project> projects2 = new ArrayList<>();
    private static ArrayList<Project> projects3 = new ArrayList<>();

    public ArrayList<Project> getProjects1() {
        return projects1;
    }

    public ArrayList<Project> getProjects2() {
        return projects2;
    }

    public ArrayList<Project> getProjects3() {
        return projects3;
    }

    public void setProjects1(ArrayList<Project> projects) {
        ProjectDAO.projects1 = projects;
    }

    public void setProjects2(ArrayList<Project> projects) {
        ProjectDAO.projects2 = projects;
    }

    public void setProjects3(ArrayList<Project> projects) {
        ProjectDAO.projects3 = projects;
    }

    @Override
    public String toString() {
        return "ProjectDAO{" +
                "projects=" + projects1 +
                "projects=" + projects2 +
                "projects=" + projects3 +
                '}';
    }
}
