package model;

import java.util.ArrayList;

/**
 * A class responsible for the creation, deletion, or other handling of accounts for the application.
 * @author Michael Pikula
 */
public class AccountManager
{
    private static AccountManager accountManager;
    private ArrayList<Account> accounts;

    /**
     * Creates a new instance of AccountManager, and can only be called once.
     */
    private AccountManager()
    {
        accounts = DataReader.loadAccounts();
    }
    /**
     * Returns the current instance of AccountManager or creates one if it does not yet exist.
     * @return An instance of AccountManager.
     */
    public static AccountManager getInstance()
    {
        if (accountManager == null)
            accountManager = new AccountManager();
        return accountManager;
    }

    /**
     * Retrieves the list of all existing accounts.
     * @return List of accounts.
     */
    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }
    /**
     * Retrieves an account from the list based on the entered username.
     * @param username The username of the account to be retrieved.
     * @return The account retrieved (null if no accounts with the username exist).
     */
    public Account getAccountByUsername(String username)
    {
        for (int i=0; i<accounts.size(); i++)
        {
            if (username.equals(accounts.get(i).getUsername()))
                return accounts.get(i);
        }
        return null;
    }

    /**
     * Attempts to log the user in with the specified username and password.
     * @param username The username to be logged in with.
     * @param password The password to be logged in with.
     * @return The account associated with the entered details (null if details incorrect).
     */
    public Account login(String username, String password)
    {    
        for (int i=0; i<accounts.size(); i++)
        {
            if (username.equals(accounts.get(i).getUsername()) && password.equals(accounts.get(i).getPassword()))
                return accounts.get(i);
        }
        return null;
    }
    /**
     * Creates a new account with the specified details and adds it to the list of accounts.
     * @param username An account identifier that also serves as a display name. Must be non-empty and unique.
     * @param password A password checked while attempting to log-in. Must be non-empty.
     * @param email The email address associated with the account. Must contain an @.
     * @param firstName The real first name of the account user.
     * @param lastName The real last name of the account user.
     * @return True if above criteria was met and account was successfully created.
     */
    public boolean createAccount(String username, String password, String email, String firstName, String lastName)
    {
        // Check valid username, password, and email.
        if (username.equals("") || password.equals("") || !isEmailValid(email))
            return false;
        // Check if username is unique.
        for (int i=0; i<accounts.size(); i++)
        {
            if (username.equals(accounts.get(i).getUsername()))
                return false;
        }
        Account newAccount = new Account(username, password, email, firstName, lastName);
        accounts.add(newAccount);
        return true;
    }
    /**
     * Removes the specified account from the list of accounts if it exists.
     * @param account Account to be removed.
     * @return true if the specified account existed in the list.
     */
    public boolean deleteAccount(Account account)
    {
        account.clearProjects();
        return accounts.remove(account);
    }
    /**
     * Checks if an entered email is valid by checking if it contains an @.
     * @param email An entered email address used while creating an account.
     * @return true if entered email contains an @.
     */
    private boolean isEmailValid(String email)
    {
        for (int i=0; i<email.length(); i++)
        {
            if (email.charAt(i) == '@')
                return true;
        }
        return false;
    }
}
