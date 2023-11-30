package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing an edit made to a task.
 * @author Michael Pikula
 */
public class Edit
{
    private LocalDateTime dateTime;
    private Account editor;
    private String description;
    private DateTimeFormatter dateFormat;

    /**
     * Creates a new edit with the specified editor and description.
     * @param editor The account that made the edit.
     * @param description A description of the edit made.
     */
    public Edit(Account editor, String description)
    {
        this.dateTime = LocalDateTime.now();
        this.editor = editor;
        this.description = description;
        this.dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd 'at' HH:mm:ss");
    }
    /**
     * Creates an edit with the specified date and time, editor, and description.
     * @param dateTime The date and time at which this edit was made.
     * @param editor The account that made the edit.
     * @param description A description of the edit made.
     */
    public Edit(LocalDateTime dateTime, Account editor, String description)
    {
        this.dateTime = dateTime;
        this.editor = editor;
        this.description = description;
        this.dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd 'at' HH:mm:ss");
    }

    public LocalDateTime getDateTime()
    {
        return this.dateTime;
    }
    public Account getEditor()
    {
        return this.editor;
    }
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Creates a string representation of this edit.
     * @return A string containing the date and time, editor, and description of this edit.
     */
    public String toString()
    {
        return "Edit on " + dateFormat.format(dateTime) + " by " + editor.getUsername() + ": " + description;
    }
}
