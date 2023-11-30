package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Al Pacicco, Michael Pikula
 */
public class Task
{
    private UUID id;
    private String name;
    private int priority;
    private ArrayList<Comment> comments;
    private ArrayList<Edit> editHistory;

    public Task(String name, int priority)
    {
        this.id = UUID.randomUUID();
        this.name = name;
        this.priority = priority;
        this.comments = new ArrayList<Comment>();
        this.editHistory = new ArrayList<Edit>();
    }
    public Task(UUID id, String name, int priority, ArrayList<Comment> comments, ArrayList<Edit> editHistory)
    {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.comments = comments;
        this.editHistory = editHistory;
    }

    public UUID getID()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public int getPriority()
    {
        return this.priority;
    }
    public ArrayList<Comment> getComments()
    {
        return this.comments;
    }
    public ArrayList<Edit> getEditHistory()
    {
        return this.editHistory;
    }

    public String toString()
    {
        return "Name: " + name + "\nPriority: " + priority;
    }
    @Override
    public boolean equals(Object task)
    {
        return task != null && this.getClass() == task.getClass()
            && id.equals(((Task)task).getID());
    }

    public void addComment(Account user, String content)
    {
        Comment newComment = new Comment(user, content);
        comments.add(newComment);
    }
    public void addComment(Comment comment)
    {
        comments.add(comment);
    }
    public boolean removeComment(Comment comment)
    {
        return comments.remove(comment);
    }
    public void addEdit(Account editor, String description)
    {
        Edit newEdit = new Edit(editor, description);
        editHistory.add(newEdit);
    }
    public void addEdit(Edit edit)
    {
        editHistory.add(edit);
    }
}
