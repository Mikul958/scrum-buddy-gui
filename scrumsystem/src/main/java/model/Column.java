package model;

import java.util.ArrayList;

/**
 * A column of the Scrum Board within a specified project.
 * @author Michael Pikula, Miles Wedeking
 */
public class Column
{
    private String title;
    private ArrayList<Task> tasks;

    /**
     * Creates a column with the specified title and initializes its list of tasks.
     * @param title The specified title of the column.
     */
    public Column(String title)
    {
        this.title = title;
        this.tasks = new ArrayList<Task>();
    }
    /**
     * Creates a column with the specified parameters.
     * @param title name of the column.
     * @param tasks provided list of tasks to be added to the column
     */
    public Column(String title, ArrayList<Task> tasks)
    {
        this.title = title;
        this.tasks = tasks;
    }
    
    // Accessors
    public String getTitle()
    {
        return this.title;
    }
    public ArrayList<Task> getTasks()
    {
        return this.tasks;
    }

    /**
     * Retrieve a task from this column with the specified name.
     * @param name The name of the task to be retrieved.
     * @return The task retrieved (null if no tasks with the name exist in the column).
     */
    public Task getTaskByName(String name)
    {
        for (int i=0; i<tasks.size(); i++)
        {
            if (name.equals(tasks.get(i).getName()))
                return tasks.get(i);
        }
        return null;
    }

    /**
     * Returns a string representation of the column.
     * @return A string containing the column's title and all of its tasks.
     */
    public String toString()
    {
        String out = "Column: " + title;
        for (int i=0; i< tasks.size(); i++)
            title += "\n" + tasks.get(i);
        return out;
    }
    /**
     * Checks if this column and the specified column are equivalent via their titles.
     * @return true if the column titles are equal.
     */
    public boolean equals(Object column)
    {
        return column != null && this.getClass() == column.getClass()
            && this.getTitle().equals(((Column)column).getTitle());
    }
    /**
     * Adds a new Task to this column.
     * @param name The name of the new Task.
     * @param priority The task's relative priority.
     */
    public void addTask(String name, int priority)
    {
        Task newTask = new Task(name, priority);
        tasks.add(newTask);
    }
    /**
     * Adds an already-created Task to this column.
     * @param task Task to be added.
     */
    public void addTask(Task task)
    {
        tasks.add(task);
    }
    /**
     * Removes the specified task from the column.
     * @param task The task to be removed from the column.
     * @return true if the specified task existed in the column.
     */
    public boolean removeTask(Task task)
    {
        return tasks.remove(task);
    }
    /**
     * Sorts all tasks in this column alphabetically.
     */
    public void orderTasks()
    {
        // Selection sort.
        for (int i=0; i<tasks.size()-1; i++)
        {
            int minIndex = i;
            for (int j=i+1; j<tasks.size(); j++)
            {
                // Get names of tasks and use Java's compareTo method.
                String currentTask = tasks.get(j).getName();
                String minTask = tasks.get(minIndex).getName();
                int compareValue = currentTask.compareTo(minTask);

                // Set currentTask as new min if it comes before minTask alphabetically.
                if (compareValue > 0)
                    minIndex = j;
            }
            // Swap tasks at i and minIndex.
            Task temp = tasks.get(i);
            tasks.set(i, tasks.get(minIndex));
            tasks.set(minIndex, temp);
        }
    }
    /**
     * Checks to see if this column contains the specified task.
     * @param task The task to be checked for.
     * @return true if this column contains the specified task.
     */
    public boolean containsTask(Task task)
    {
        for (int i=0; i<tasks.size(); i++)
        {
            if (tasks.get(i).equals(task))
                return true;
        }
        return false;
    }
}