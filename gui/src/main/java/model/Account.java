package model;

import java.util.ArrayList;

/**
 * An account of a user, containing various information as well as a list of the user's projects.
 * @author Michael Pikula
 */
public class Account
{
    private String username;  // Username must be unique for account creation, no need for UUIDs.
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private ArrayList<Project> projects;

    /**
     * Creates an account with the specified information.
     * @param username The username of the account.
     * @param password The password of the account.
     * @param email The email address associated with the account.
     * @param firstName The account user's real first name.
     * @param lastName The account user's real last name.
     */
    public Account(String username, String password, String email, String firstName, String lastName)
    {
        this.username = username;  // Uniqueness must be checked externally.
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.projects = new ArrayList<Project>();
    }

    // Getters and setters
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public ArrayList<Project> getProjects()
    {
        return this.projects;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setName(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public void addProject(Project project)
    {
        projects.add(project);
    }
    public boolean removeProject(Project project)
    {
        return projects.remove(project);
    }
    /**
     * Clears this account's project list and removes them as a contributor from all projects on the list. Intended to be used on account deletion.
     */
    public void clearProjects()
    {
        // NOTE: project.removeContributor already removes both ways. This means that for-loop needs to progress backwards, otherwise indices will shift mid-loop and lead to skips.
        for (int i=projects.size()-1; i >= 0; i--)
            projects.get(i).removeContributor(this);
        // projects.clear() is not needed because of above :)
    }

    /**
     * Creates a string containing all account information except for the password.
     * @return A string containing account information.
     */
    public String toString()
    {
        return "Username: " + username
        + "\nEmail: " + email
        + "\nName: " + firstName + " " + lastName;
    }
    /**
     * Checks if this account and the specified account are equivalent via their usernames.
     * @param account The account to be compared to.
     * @return true if the accounts are equivalent.
     */
    @Override
    public boolean equals(Object account)
    {
        return account != null && this.getClass() == account.getClass()
            && username.equals(((Account)account).getUsername());
    }
}