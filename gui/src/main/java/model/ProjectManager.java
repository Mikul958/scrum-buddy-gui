package model;

import java.util.UUID;
import java.util.ArrayList;

/**
 * A class responsible for the creation and deletion of projects for the application.
 * @author Michael Pikula, Miles Wedeking.
 */
public class ProjectManager
{
    private static ProjectManager projectManager;
    private ArrayList<Project> projects;

    /**
     * Creates a new instance of ProjectManager, and can only be called once.
     */
    private ProjectManager()
    {
        projects = DataReader.loadProjects();
    }
    /**
     * Returns the current instance of ProjectManager or creates one if it does not yet exist.
     * @return An instance of ProjectManager.
     */
    public static ProjectManager getInstance()
    {
        if (projectManager == null)
            projectManager = new ProjectManager();
        return projectManager;
    }

    // Accessors

    /**
     * Returns the list of all existing projects.
     * @return List of projects.
     */
    public ArrayList<Project> getProjects()
    {
        return this.projects;
    }
    /**
     * Retrieves the project with the specified UUID.
     * @param id Java UUID of the project to be retrieved.
     * @return The project retrieved (null if no accounts with the ID exist).
     */
    public Project getProjectByID(UUID id)
    {
        for (int i=0; i<projects.size(); i++)
        {
            if (id.equals(projects.get(i).getID()))
                return projects.get(i);
        }
        return null;
    }
    
    /**
     * Creates a new project with the specified details and adds it to the list of projects. Project name does not need to be unique.
     * @param title you want to name the project
     * @param category the project will be
     * @param owner tied to the creation of the project
     */
    public void createProject(String title, Category category, Account owner)
    {
        Project newProject = new Project(title, category, owner);
        projects.add(newProject);
    }
    /**
     * Remove the specified project from the list of projects if it exists.
     * @param project Project to be removed.
     * @return true if the specified project existed in the list.
     */
    public boolean deleteProject(Project project)
    {
        project.clearContributors();
        return projects.remove(project);
    }
}
