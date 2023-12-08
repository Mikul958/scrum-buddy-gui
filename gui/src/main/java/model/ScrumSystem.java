package model;

import java.util.ArrayList;

/**
 * A Facade class responsible for overall management of the software, including accounts, projects, which are open at any time, and more.
 * @author Michael Pikula
 */
public class ScrumSystem
{
    private AccountManager accountManager;
    private ProjectManager projectManager;
    private Account currentAccount;
    private Project currentProject;
    private static ScrumSystem system;

    private ScrumSystem()
    {
        accountManager = AccountManager.getInstance();
        projectManager = ProjectManager.getInstance();
    }

    public static ScrumSystem getInstance()
    {
        if (system == null)
            system = new ScrumSystem();
        return system;
    }

    // Accessors
    public Account getCurrentAccount()
    {
        return this.currentAccount;
    }
    public Project getCurrentProject()
    {
        return this.currentProject;
    }
    /**
     * Get the list of all the currently logged-in user's projects.
     * @return The list of all projects that the current user is a contributor to.
     */
    public ArrayList<Project> getCurrentAccountProjects()
    {
        return currentAccount.getProjects();
    }

    // Data

    /**
     * Saves all accounts and projects to their respective JSON files.
     * @return true if all data was saved successfully.
     */
    public boolean saveData()
    {
        if (DataWriter.saveAccounts() && DataWriter.saveProjects())
            return true;
        return false;
    }

    // Account operations

    /**
     * Attempts to log in an account with the entered details. Sets currentUser to the entered account if successful.
     * @param username The username of the account being logged in.
     * @param password The password of the account being logged in.
     * @return true if an account with the entered username exists and passwords match.
     */
    public boolean login(String username, String password)
    {
        Account temp = accountManager.login(username, password);
        if (temp == null)
            return false;
        currentAccount = temp;
        return true;
    }
    /**
     * Logs the current user out. Sets currentUser and currentProject to null.
     */
    public void logout()
    {
        currentAccount = null;
        currentProject = null;
    }
    /**
     * Creates a new account with the specified details. Username must be unique for account creation.
     * @param username An account identifier that also serves as a display name.
     * @param password A password checked while attempting to log in.
     * @param email The email address associated with the account.
     * @param firstName The real first name of the account user.
     * @param lastName The real last name of the account user.
     * @return true if the username was unique and the account was successfully created.
     */
    public boolean createAccount(String username, String password, String email, String firstName, String lastName)
    {
        return accountManager.createAccount(username, password, email, firstName, lastName);
    }
    /**
     * Deletes and logs out the account currently in use.
     * @return true if an account was logged in and successfully deleted.
     */
    public boolean deleteCurrentAccount()
    {
        if (currentAccount == null)
            return false;
        boolean removed = accountManager.deleteAccount(currentAccount);
        if (removed)
            currentAccount = null;
        return removed;
    }

    // Project Operations

    /**
     * Sets the current project equal to the specified project if the currentUser is a contributor.
     * @param project The project to be opened.
     * @return true if the currentUser is a contributor to the specified project.
     */
    public boolean openProject(Project project)
    {
        if (currentAccount != null && !currentAccount.getProjects().contains(project))
            return false;
        currentProject = project;
        return true;
    }
    /**
     * Sets the current project equal to null.
     */
    public void closeProject()
    {
        currentProject = null;
    }
    /**
     * Creates a project with the specified title and category and assigns currentUser as owner.
     * @param title The title of the new project.
     * @param category The category of the new project.
     */
    public void createProject(String title, Category category)
    {
        if (currentAccount == null)
            return;
        projectManager.createProject(title, category, currentAccount);  // Create a project with currentUser as the owner.
    }
    /**
     * Deletes the current project and unlinks all accounts.
     * @return true if the project was successfully deleted.
     */
    public boolean deleteProject()
    {
        boolean removed = projectManager.deleteProject(currentProject);
        if (removed)
            currentProject = null;
        return removed;
    }
    /**
     * Sets the title of the current project to the specified title.
     * @param title New title of the project.
     */
    public void setProjectTitle(String title)
    {
        currentProject.setTitle(title);
    }
    /**
     * Sets the category of the current project to the specified category.
     * @param category New category of the project, must be obtained from string or int before calling.
     */
    public void setProjectCategory(Category category)
    {
        currentProject.setCategory(category);
    }
    /**
     * Adds the account with the entered username as a contributor to the current project, if the account exists and is not already a contributor.
     * @param username The username of the account to be added.
     * @return true if the account with the entered username exists and was added successfully.
     */
    public boolean addProjectContributor(String username)
    {
        Account toAdd = accountManager.getAccountByUsername(username);
        if (toAdd == null)
            return false;
        return currentProject.addContributor(toAdd);
    }
    /**
     * Removes the account with the entered username as a contributor from the current project, if the account exists and is currently a contributor.
     * @param username The username of the account to be removed.
     * @return true if the account with the entered username exists and is in the list of contributors.
     */
    public boolean removeProjectContributor(String username)
    {
        Account toRemove = accountManager.getAccountByUsername(username);
        if (toRemove == null)
            return false;
        return currentProject.removeContributor(toRemove);
    }
    /**
     * Adds a column with the specified title to the current project, if a column with that title doesn't already exist.
     * @param columnTitle The title of the new column.
     * @return true if the column was successfully added.
     */
    public boolean addProjectColumn(String columnTitle)
    {
        return currentProject.addColumn(columnTitle);
    }
    /**
     * Removes the column with the specified title from the current project, if it exists.
     * @param columnTitle The title of the column to be removed.
     * @return true if a column with the specified title existed in the project.
     */
    public boolean removeProjectColumn(String columnTitle)
    {
        return currentProject.removeColumn(columnTitle);
    }
    /**
     * Moves the column at index from to index to in the current project, shifting all other columns as necessary.
     * @param from The index of the column to be moved.
     * @param to The index that the column will be moved to.
     * @return true if both indices were in-bounds and the column was successfully moved.
     */
    public boolean moveColumn(int from, int to)
    {
        return currentProject.moveColumn(from, to);
    }
    
    // Project task operations.

    /**
     * Adds a task to the specified column in the current project, if the column exists.
     * @param columnTitle Title of the column to add task to.
     * @param name The name of the new task.
     * @param priority The priority of the new task.
     * @return true if the column with the specified title exists.
     */
    public boolean addProjectTask(String columnTitle, String name, int priority)
    {
        return currentProject.addTask(columnTitle, name, priority);
    }
    /**
     * Adds a task to the specified column in the current project.
     * @param column Column to add task to.
     * @param name The name of the new task.
     * @param priority The priority of the new task.
     */
    public void addProjectTask(Column column, String name, int priority)
    {
        currentProject.addTask(column, name, priority);
    }
    /**
     * Removes a task from the specified column in the current project, if the column exists.
     * @param columnTitle Title of the column to remove task from.
     * @param task The task to be removed, must be obtained from UI before calling.
     * @return true if the column exists and the task exists in that column.
     */
    public boolean removeProjectTask(String columnTitle, Task task)
    {
        return currentProject.removeTask(columnTitle, task.getName());
    }
    public boolean removeProjectTask(Column column, Task task)
    {
        return currentProject.removeTask(column, task);
    }
    /**
     * Moves a task in the current project from one specified column to another.
     * @param task The task to be moved, must be obtained from UI before calling.
     * @param columnTitleFrom Title of the original column.
     * @param columnTitleTo Title of the destination column.
     * @return true if both columns exist and the specified task exists in original column.
     */
    public boolean moveProjectTask(Task task, String columnTitleFrom, String columnTitleTo)
    {
        if (currentProject.moveTask(task, columnTitleFrom, columnTitleTo))
        {
            task.addEdit(currentAccount, "Moved to " + columnTitleTo);
            return true;
        }
        return false;
    }

    // Project comment operations.

    /**
     * Add a comment to the current project.
     * @param user Account making the comment.
     * @param content Content of the comment.
     */
    public void addProjectComment(String content)
    {
        currentProject.addComment(currentAccount, content);
    }
    /**
     * Remove a comment from the current project.
     * @param comment Comment to be removed, must be obtained from UI before calling.
     */
    public void removeProjectComment(Comment comment)
    {
        currentProject.removeComment(comment);
    }
    /**
     * Adds a comment to the specified task if the task exists in the current project.
     * @param task The task to add the comment to, must be obtained from UI before calling.
     * @param user The user making the comment.
     * @param content The content of the comment.
     * @return true if the specified task exists in the current project.
     */
    public boolean addTaskComment(Task task, String content)
    {
        return currentProject.addTaskComment(task, currentAccount, content);
    }
    /**
     * Removes a comment from the specified task if the task exists in the current project.
     * @param task The task to remove the comment from, must be obtained from UI before calling.
     * @param comment The comment to be removed, must be obtained from UI before calling.
     * @return true if the specified task exists in the current project and the specified comment existed in the task.
     */
    public boolean removeTaskComment(Task task, Comment comment)
    {
        return currentProject.removeTaskComment(task, comment);
    }
}
