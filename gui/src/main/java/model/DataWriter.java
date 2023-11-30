package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A class responsible for saving system information to respective JSON files.
 */
public class DataWriter extends DataConstants
{
    /**
     * Saves the system-wide list of accounts to Accounts.json
     * @return true if accounts were successfully written.
     */
    public static boolean saveAccounts()
    {
        // Get system-wide list of accounts and initialize outer JSONArray.
        AccountManager manager = AccountManager.getInstance();
        ArrayList<Account> accounts = manager.getAccounts();
        JSONArray accountsJSON = new JSONArray();

        // Populate a JSONObject with account details and add object to JSONArray for all accounts.
        for (int i=0; i<accounts.size(); i++)
        {
            Account currentAccount = accounts.get(i);
            JSONObject accountJSON = new JSONObject();
            accountJSON.put(USERNAME, currentAccount.getUsername());
            accountJSON.put(PASSWORD, currentAccount.getPassword());
            accountJSON.put(EMAIL, currentAccount.getEmail());
            accountJSON.put(FIRST_NAME, currentAccount.getFirstName());
            accountJSON.put(LAST_NAME, currentAccount.getLastName());

            accountsJSON.add(accountJSON);
        }

        try
        {
            FileWriter writer = new FileWriter(ACCOUNTS_FILE);

            writer.write(accountsJSON.toJSONString());
            writer.flush();
            writer.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Saves the system-wide list of projects and tasks to Projects.json and Tasks.json.
     * @return true if projects and tasks were successfully written.
     */
    public static boolean saveProjects()
    {
        // List to keep track of tasks as projects are saved, will be saved at the end of this method.
        ArrayList<Task> tasksToSave = new ArrayList<Task>();
        
        // Get system-wide list of projects and initalize outer JSONArray.
        ProjectManager manager = ProjectManager.getInstance();
        ArrayList<Project> projects = manager.getProjects();
        JSONArray projectsJSON = new JSONArray();

        for (int i=0; i<projects.size(); i++)
        {
            // Create a JSONObject for the current project.
            Project currentProject = projects.get(i);
            JSONObject projectJSON = new JSONObject();

            // Get the ID, convert to String, and add to JSONObject
            String idString = currentProject.getID().toString();
            projectJSON.put(PROJECT_ID, idString);

            // Add title to JSONObject
            projectJSON.put(PROJECT_TITLE, currentProject.getTitle());
            
            // Get the category's description and add to JSONObject
            String categoryName = currentProject.getCategory().description;
            projectJSON.put(PROJECT_CATEGORY, categoryName);

            // Get the username of the owner and add to JSONObject
            String ownerName = currentProject.getOwner().getUsername();
            projectJSON.put(PROJECT_OWNER, ownerName);

            // Put the usernames of all contributors into a JSONArray and add to JSONObject.
            ArrayList<Account> contributors = currentProject.getContributors();
            JSONArray contributorsJSON = saveContributors(contributors);
            projectJSON.put(PROJECT_CONTRIBUTORS, contributorsJSON);

            // Puts all project columns into a JSONArray and adds to JSONObject and adds each task to the tasksToSave list.
            ArrayList<Column> columns = currentProject.getColumns();
            JSONArray columnsJSON = saveColumns(columns, tasksToSave);
            projectJSON.put(PROJECT_COLUMNS, columnsJSON);

            // Put all comments on the project into a JSONArray and add to JSONObject.
            ArrayList<Comment> comments = currentProject.getComments();
            JSONArray commentsJSON = saveComments(comments);
            projectJSON.put(COMMENTS, commentsJSON);

            // Add finished JSONObject to the outer JSONArray.
            projectsJSON.add(projectJSON);
        }

        try
        {
            FileWriter writer = new FileWriter(PROJECTS_FILE);

            writer.write(projectsJSON.toJSONString());
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        
        // Saves the list of tasks accumulated throughout all projects to Tasks.json.
        return saveTasks(tasksToSave);
    }
    /**
     * Takes in the system-wide list of tasks and saves it to Tasks.json.
     * @param tasks The system-wide list of tasks.
     * @return true if tasks were successfully written.
     */
    private static boolean saveTasks(ArrayList<Task> tasks)
    {
        JSONArray tasksJSON = new JSONArray();
        for (int i=0; i<tasks.size(); i++)
        {
            Task currentTask = tasks.get(i);
            JSONObject taskJSON = new JSONObject();

            // Add basic task information to JSONObject.
            String idString = currentTask.getID().toString();
            taskJSON.put(TASK_ID, idString);
            taskJSON.put(TASK_NAME, currentTask.getName());
            taskJSON.put(TASK_PRIORITY, currentTask.getPriority());

            // Add JSONArray of task comments to JSONObject, saveComments() works for both projects and tasks.
            ArrayList<Comment> comments = currentTask.getComments();
            JSONArray commentsJSON = saveComments(comments);
            taskJSON.put(COMMENTS, commentsJSON);

            // Add JSONArray of task edits to JSONObject
            ArrayList<Edit> edits = currentTask.getEditHistory();
            JSONArray editsJSON = saveEdits(edits);
            taskJSON.put(TASK_EDITS, editsJSON);

            // Add finished JSONObject to the outer JSONArray
            tasksJSON.add(taskJSON);
        }

        try
        {
            FileWriter writer = new FileWriter(TASKS_FILE);

            writer.write(tasksJSON.toJSONString());
            writer.flush();
            writer.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Takes in a list of contributors and returns a JSONArray of their usernames.
     * @param contributors A list of contributors for a given project.
     * @return A JSONArray containing the usernames of project contributors.
     */
    private static JSONArray saveContributors(ArrayList<Account> contributors)
    {
        JSONArray contributorsJSON = new JSONArray();
        for (int i=0; i<contributors.size(); i++)
        {
            String contributorName = contributors.get(i).getUsername();
            contributorsJSON.add(contributorName);
        }
        return contributorsJSON;
    }
    /**
     * Builds a JSONArray of columns containing a title and a JSONArray of Java UUIDs corresponding to tasks.
     * @param columns The list of columns for a given project.
     * @param tasksToSave A list of tasks that keeps track of all tasks encountered while saving projects.
     * @return A JSONArray containing JSONObjects with each column's information for a given project.
     */
    private static JSONArray saveColumns(ArrayList<Column> columns, ArrayList<Task> tasksToSave)
    {
        JSONArray columnsJSON = new JSONArray();
        for (int i=0; i<columns.size(); i++)
        {
            Column currentColumn = columns.get(i);
            JSONObject columnJSON = new JSONObject();

            // Add column title to column JSONObject
            columnJSON.put(COLUMN_TITLE, currentColumn.getTitle());

            // Loop through all tasks in column, get UUIDs, and add corresponding string to JSONArray of task UUIDs.
            ArrayList<Task> columnTasks = currentColumn.getTasks();
            JSONArray columnTasksJSON = new JSONArray();
            for (int j=0; j<columnTasks.size(); j++)
            {
                Task currentTask = columnTasks.get(j);
                String idString = currentTask.getID().toString();
                columnTasksJSON.add(idString);

                // Adds the current task to a system-wide list that will be saved later.
                tasksToSave.add(currentTask);
            }
            // Add JSONArray of task UUIDs to column JSONObject.
            columnJSON.put(COLUMN_TASKS, columnTasksJSON);

            // Add finished JSONObject to outer JSONArray.
            columnsJSON.add(columnJSON);
        }
        return columnsJSON;
    }
    /**
     * Builds a JSONArray of comments containing a dateTime, user, and content.
     * @param comments The list of comments for a given project or task.
     * @return A JSONArray containing JSONObjects with each comment's information.
     */
    private static JSONArray saveComments(ArrayList<Comment> comments)
    {
        JSONArray commentsJSON = new JSONArray();
        for (int i=0; i<comments.size(); i++)
        {
            Comment currentComment = comments.get(i);
            JSONObject commentJSON = new JSONObject();

            // Put dateTime in specified format and add resulting string to JSONObject
            String timeString = TIME_FORMAT.format(currentComment.getDateTime());
            commentJSON.put(TIME, timeString);

            // Get commenter's username and add to comment JSONObject.
            String username = currentComment.getUser().getUsername();
            commentJSON.put(COMMENT_USER, username);

            // Add comment content to JSONObject.
            commentJSON.put(COMMENT_CONTENT, currentComment.getContent());

            // Add finished JSONObject to outer JSONArray.
            commentsJSON.add(commentJSON);
        }
        return commentsJSON;
    }
    /**
     * Builds a JSONArray of comments containing a dateTime, editor, and description.
     * @param edits The edit history for a given task.
     * @return A JSONArray contanining JSONObjects with each edit's information.
     */
    private static JSONArray saveEdits(ArrayList<Edit> edits)
    {
        JSONArray editsJSON = new JSONArray();
        for (int i=0; i<edits.size(); i++)
        {
            Edit currentEdit = edits.get(i);
            JSONObject editJSON = new JSONObject();

            String timeString = TIME_FORMAT.format(currentEdit.getDateTime());
            editJSON.put(TIME, timeString);

            String username = currentEdit.getEditor().getUsername();
            editJSON.put(EDIT_EDITOR, username);

            editJSON.put(EDIT_DESCRIPTION, currentEdit.getDescription());

            editsJSON.add(editJSON);
        }
        return editsJSON;
    }
}
