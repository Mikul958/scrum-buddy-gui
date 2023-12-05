package model;

import java.time.format.DateTimeFormatter;

/**
 * A class containing constants for DataReader and DataWriter.
 * @author Michael Pikula
 */
public abstract class DataConstants
{
    // Time and formatter for displaying/parsing time.
    protected static final String TIME = "dateTime";
    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd 'at' HH:mm:ss");
    
    // Accounts.json
    protected static final String ACCOUNTS_FILE = "gui/src/main/java/data/Accounts.json";
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "password";
    protected static final String EMAIL = "email";
    protected static final String FIRST_NAME = "firstName";
    protected static final String LAST_NAME = "lastName";


    // Projects.json
    protected static final String PROJECTS_FILE = "gui/src/main/java/data/Projects.json";
    protected static final String PROJECT_ID = "pUUID";
    protected static final String PROJECT_TITLE = "title";
    protected static final String PROJECT_CATEGORY = "category";
    protected static final String PROJECT_OWNER = "owner";
    protected static final String PROJECT_CONTRIBUTORS = "contributors";
    protected static final String PROJECT_COLUMNS = "columns";
    protected static final String COLUMN_TITLE = "columnTitle";
    protected static final String COLUMN_TASKS = "tasks";

    // Comments (Shared between Projects and Tasks)
    protected static final String COMMENTS = "comments";
    protected static final String COMMENT_USER = "user";
    protected static final String COMMENT_CONTENT = "content";


    // Tasks.json
    protected static final String TASKS_FILE = "gui/src/main/java/data/Tasks.json";
    protected static final String TASK_ID = "tUUID";
    protected static final String TASK_TYPE = "type";
    protected static final String TASK_NAME = "name";
    protected static final String TASK_PRIORITY = "priority";
    protected static final String TASK_EDITS = "editHistory";
    protected static final String EDIT_EDITOR = "editor";
    protected static final String EDIT_DESCRIPTION = "description";
}
