package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A comment made on a project or project task.
 * @author Miles Wedeking, Michael Pikula
 */
public class Comment
{
    private LocalDateTime dateTime;
    private Account user;
    private String content;
    private DateTimeFormatter dateFormat;

    /**
     * Creates a new comment with the specified user and contents.
     * @param user The user making the comment.
     * @param content The comment being made.
     */
    public Comment(Account user, String content)
    {
        this.dateTime = LocalDateTime.now();  // Set date to time of construction.
        this.user = user;
        this.content = content;
        this.dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd 'at' HH:mm:ss");
    }
    /**
     * Creates a content from a file with the specified time, user, and contents.
     * @param dateTime The date and time that the comment was made.
     * @param user The user making the comment.
     * @param content The comment being made.
     */
    public Comment (LocalDateTime dateTime, Account user, String content)
    {
        this.dateTime = dateTime;
        this.user = user;
        this.content = content;
        this.dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd 'at' HH:mm:ss");
    }

    public LocalDateTime getDateTime()
    {
        return this.dateTime;
    }
    public Account getUser()
    {
        return this.user;
    }
    public String getContent()
    {
        return this.content;
    }

    /**
     * Creates a string representation of the comment.
     * @return A string containing the user, time, and content of the comment.
     */
    public String toString()
    {
        String out = "";

        if (user != null)
            out += user.getUsername();
        else
            out += "anonymous";

        out += " on " + dateFormat.format(dateTime) + ": " + content;
        return out;
    }
    public boolean equals(Object comment)
    {
        return comment != null && this.getClass() == comment.getClass()
            && this.dateTime.equals(((Comment)comment).getDateTime())
            && this.user.equals(((Comment)comment).getUser())
            && this.content.equals(((Comment)comment).getContent());
    }
}
