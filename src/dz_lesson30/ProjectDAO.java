package dz_lesson30;

import java.util.ArrayList;

public class ProjectDAO {
    private static ArrayList<Project> projects = new ArrayList<>();

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        ProjectDAO.projects = projects;
    }

    @Override
    public String toString() {
        return "ProjectDAO{" +
                "projects=" + projects +
                '}';
    }
}
