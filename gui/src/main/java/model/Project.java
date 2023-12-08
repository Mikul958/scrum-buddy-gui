package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 * @author Miles Wedeking, Al Pacicco, Michael Pikula
 */
public class Project
{
    private UUID id;
    private String title;
    private Category category;
    private Account owner;
    private ArrayList<Account> contributors;
    private ArrayList<Column> columns;
    private ArrayList<Comment> comments;

    /**
     * Creates a fresh project with the specified basic information and handles Account-Project linking.
     * @param title The title of the new project.
     * @param category The category of the new project.
     * @param owner The account creating the new project.
     */
    public Project(String title, Category category, Account owner)
    {
        this.id = UUID.randomUUID();
        this.title = title;
        this.category = category;
        this.owner = owner;
        contributors = new ArrayList<Account>();
        
        // Add owner to list of contributors and this project to owner's project list.
        contributors.add(owner);
        owner.addProject(this);

        columns = new ArrayList<Column>();
        comments = new ArrayList<Comment>();
    }
    /**
     * Creates a project with existing contributors, columns, and comments and handles Account-Project linking. Used when loading project from file.
     * @param id The Java UUID of the project.
     * @param title The title of the project.
     * @param category The category of the project.
     * @param owner The account that created the project.
     * @param contributors The list of all contributors to the project, including the owner.
     * @param columns The list of all of the project's columns.
     * @param comments The list of all comments on the project.
     */
    public Project(UUID id, String title, Category category, Account owner, ArrayList<Account> contributors, ArrayList<Column> columns, ArrayList<Comment> comments)
    {
        this.id = id;
        this.title = title;
        this.category = category;
        this.owner = owner;

        // Link contributors with project.
        this.contributors = contributors;
        for (int i=0; i<contributors.size(); i++)
            contributors.get(i).addProject(this);

        this.columns = columns;
        this.comments = comments;
    }


    // Accessors and mutators
    public UUID getID()
    {
        return this.id;
    }
    public String getTitle()
    {
        return this.title;
    }
    public Category getCategory()
    {
        return this.category;
    }
    public Account getOwner()
    {
        return this.owner;
    }
    public ArrayList<Account> getContributors()
    {
        return this.contributors;
    }
    public ArrayList<Column> getColumns()
    {
        return this.columns;
    }
    public ArrayList<Comment> getComments()
    {
        return this.comments;
    }
    /**
     * Counts the total number of tasks in this project.
     * @return The total number of tasks.
     */
    public int getTotalTasks()
    {
        int total = 0;

        // Loop through project columns, get task list for each column, add its size to total.
        for (int i=0; i<columns.size(); i++)
            total += columns.get(i).getTasks().size();
        return total;
    }
    /**
     * Gets the column from this project with the specified title.
     * @param columnTitle The title of the column to be returned.
     * @return The column with the specified title (null if none found).
     */
    public Column getColumnByTitle(String columnTitle)
    {
        for(int i=0; i<columns.size(); i++)
        {
            Column currentColumn = columns.get(i);
            if(currentColumn.getTitle().equals(columnTitle))
                return currentColumn;
        }
        return null;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setCategory(Category category)
    {
        this.category = category;
    }

    /**
     * Checks if this project and the specified project are equivalent via their UUIDs.
     * @return true if the project IDs are equal.
     */
    @Override
    public boolean equals(Object project)
    {
        return project != null && this.getClass() == project.getClass()
            && id.equals(((Project)project).getID());
    }

    // Contributors

    /**
     * Add the specified account as a contributor to this project if account is not already a contributor, and adds this project to their project list.
     * @param account Account to be added.
     * @return true if the specified account was added successfully.
     */
    public boolean addContributor(Account account)
    {
        if (isContributor(account))
            return false;
        contributors.add(account);
        account.addProject(this);
        return true;
    }
    /**
     * Remove the specified account as a contributor from this project and this project from their project list.
     * @param account Account to be removed.
     * @return true if the specified account was in the list of contributors.
     */
    public boolean removeContributor(Account account)
    {
        boolean removed = contributors.remove(account);
        if (removed)
            account.removeProject(this);
        return removed;

    }
    /**
     * Checks to see if the specified account is in the list of contributors.
     * @param account Account to be checked.
     * @return true if the specified account is in the list.
     */
    public boolean isContributor(Account account)
    {       
        for(int i=0; i<contributors.size(); i++)
        {   
            Account currentAccount = contributors.get(i);         
            if (currentAccount.equals(account))
                return true;
        }
        return false;
    }
    /**
     * Clears the list of contributors and removes this project from each contributor's project list. Intended to be used on project deletion.
     */
    public void clearContributors()
    {
        for (int i=0; i<contributors.size(); i++)
            contributors.get(i).removeProject(this);
        contributors.clear();
    }

    // Columns

    /**
     * Adds a column to the project with the specified title if a column with this title is not already in the project.
     * @param title Title of the new column.
     * @return true if the column was successfully added.
     */
    public boolean addColumn(String title)
    {
        for (int i=0; i<columns.size(); i++)
        {
            String currentColumnTitle = columns.get(i).getTitle();
            if (currentColumnTitle.equals(title))
                return false;
        }
        Column newColumn = new Column(title);
        columns.add(newColumn);
        return true;
    }
    /**
     * Add a column to a project after checking to make sure a column with that title is not already in the project.
     * @param column Column to be added to the project.
     * @return true if the column was successfully added.
     */
    public boolean addColumn(Column column)
    {
        for (int i=0; i<columns.size(); i++)
        {
            String currentColumnTitle = columns.get(i).getTitle();
            if (currentColumnTitle.equals(column.getTitle()))
                return false;
        }
        columns.add(column);
        return true;
    }
    /**
     * Removes the column with the specified title from the project.
     * @param title Title of the column to be removed.
     * @return true if a column with the specified title existed in the project.
     */
    public boolean removeColumn(String title)
    {
        for (int i=0; i<columns.size(); i++)
        {
            Column currentColumn = columns.get(i);
            if (title.equals(currentColumn.getTitle()))
                return columns.remove(currentColumn);
        }
        return false;
    }
    /**
     * Removes the specified column from the project.
     * @param column Column to be removed.
     * @return true if the specified column existed in the project.
     */
    public boolean removeColumn(Column column)
    {
        return columns.remove(column);
    }
    /**
     * Moves the column at index from to index to, shifting all other columns as necessary.
     * @param from The index of the column to be moved.
     * @param to The index that the column will be moved to.
     * @return true if both indices were in-bounds and the column was successfully moved.
     */
    public boolean moveColumn(int from, int to)
    {  
        // Check entered indices are not out of bounds.
        if (from < 0 || from >= columns.size() || to < 0 || to >= columns.size())
            return false;

        // Store column at from and remove it, add stored column at to (and shift everything after to the right).
        Column movedColumn = columns.get(from);
        columns.remove(from);
        columns.add(to, movedColumn);
        return true;
    }

    // Tasks

    /**
     * Adds a task to the specified column, with the specified name and priority, if the column exists.
     * @param columnTitle Title of the column to add task to.
     * @param name The name of the new task.
     * @param priority The priority of the new task.
     * @return true if the specified column existed.
     */
    public boolean addTask(String columnTitle, String name, int priority)
    {
        Column addTo = getColumnByTitle(columnTitle);
        if (addTo == null)
            return false;
        addTo.addTask(name, priority);
        return true;
    }
    /**
     * Adds a task to the specified column, with the specified name and priority, if the column exists.
     * @param column Column to add task to.
     * @param name The name of the new task.
     * @param priority The priority of the new task.
     */
    public void addTask(Column column, String name, int priority)
    {
        column.addTask(name, priority);
    }
    /**
     * Removes the first task with the specified name from the specified column.
     * @param columnTitle Title of the column to remove task from.
     * @param taskName The name of the task to be removed.
     * @return true if the column exists and the task exists in that column.
     */
    public boolean removeTask(String columnTitle, String name)
    {
        Column removeFrom = getColumnByTitle(columnTitle);
        if (removeFrom == null)
            return false;
        Task toRemove = removeFrom.getTaskByName(name);
        if (toRemove == null)
            return false;
        return removeFrom.removeTask(toRemove);
    }
    /**
     * Removes the specified task from the specified column.
     * @param columnTitle The title of the column to remove task from.
     * @param task The task to be removed.
     * @return true if the column exists and the task exists in that column.
     */
    public boolean removeTask(String columnTitle, Task task)
    {
        Column removeFrom = getColumnByTitle(columnTitle);
        if (removeFrom == null)
            return false;
        return removeFrom.removeTask(task);
    }
    /**
     * Removes the specified task from the specified column.
     * @param column The column to remove task from.
     * @param task The task to be removed.
     * @return true if the column exists and the task exists in that column.
     */
    public boolean removeTask(Column column, Task task)
    {
        return column.removeTask(task);  
    }
    /**
     * Moves a task from one specified column to another.
     * @param task The task to be moved.
     * @param columnNameFrom Title of the original column.
     * @param columnNameTo Title of the destination column.
     * @return true if both columns exist and the specified task exists in original column.
     */
    public boolean moveTask(Task task, String columnTitleFrom, String columnTitleTo)
    {
        Column fromColumn = getColumnByTitle(columnTitleFrom);
        Column toColumn = getColumnByTitle(columnTitleTo);
        if (fromColumn == null || toColumn == null)
            return false;
        boolean taskRemoved = fromColumn.removeTask(task);
        if (!taskRemoved)
            return false;
        toColumn.addTask(task);
        return true;
    }
    /**
     * Checks to see if the project contains the specified task.
     * @param task The task to be checked for.
     * @return true if this project contains the specified task.
     */
    public boolean containsTask(Task task)
    {
        for (int i=0; i<columns.size(); i++)
        {
            if (columns.get(i).containsTask(task))
                return true;
        }
        return false;
    }

    // Comments

    /**
     * Add a comment to this project.
     * @param user Account making the comment.
     * @param content Content of the comment.
     */
    public void addComment(Account user, String content)
    {
        Comment newComment = new Comment(user, content);
        comments.add(newComment);
    }
    /**
     * Remove a comment from this project.
     * @param comment Comment to be removed.
     */
    public void removeComment(Comment comment)
    {
        comments.remove(comment);
    }
    /**
     * Adds a comment to the specified task if it exists in this project.
     * @param task The task to add the comment to.
     * @param user The user making the comment.
     * @param content The content of the comment.
     * @return true if the specified task exists in this project.
     */
    public boolean addTaskComment(Task task, Account user, String content)
    {
        if (!containsTask(task))
            return false;
        Comment newComment = new Comment(user, content);
        task.addComment(newComment);
        return true;
    }
    /**
     * Removes a comment from the specified task if the task exists in this project.
     * @param task The task to remove the comment from.
     * @param comment The comment to be removed.
     * @return true if the specified task exists in this project and the specified comment existed in the task.
     */
    public boolean removeTaskComment(Task task, Comment comment)
    {
        if (!containsTask(task))
            return false;
        return task.removeComment(comment);
    }
}