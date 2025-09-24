package projects.bank;

public class Bank
{
    // Data
    private Account[] accounts;

    // Constructor
    public Bank(
        Account[] accounts
    ){
        if (accounts != null)
        {
            this.accounts = accounts;
        }
        // Throw error if accounts array is null
        else
        {
            throw new IllegalArgumentException("Array of accounts must not be null.");
        }
    }

    // Instance methods
    public void add(Account newAcct) // Adds a new account to bank. New account's ID must be unique.
    {
        // Create new accounts array
        Account[] temp = new Account[this.accounts.length + 1];
        
        // Copy existing accounts to new array
        for (int i = 0; i < this.accounts.length; i ++)
        {
            // Check that new account ID is unique
            if (newAcct.getID() != this.accounts[i])
            {
                temp[i] = this.accounts[i];
            }
            // Cancel account creation and print feedback message if ID is in use
            else
            {
                System.out.println("Account could not be added to bank; specified ID already in use.");
                break;
            }
        }

        // Add new account at end of new array
        temp[temp.length - 1] = newAcct;

        // Save new array to accounts variable
        this.accounts = temp;
    }

    public void find(String acctID) // Finds a bank account with the specified ID. Returns true if the account exists; returns false otherwise.
    {
        
    }
}