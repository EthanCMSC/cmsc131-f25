package projects.bank;

public class Bank
{
    // Instance variables
    private Account[] accounts;

    // Constructors
    public Bank()
    {
        this.accounts = new Account[0];
    }

    // Instance methods
    public boolean add(Account newAcct) // Adds a new account to bank. New account's ID must be unique. Returns true if successful; returns false if failed.
    {
        // Create new accounts array
        Account[] temp = new Account[this.accounts.length + 1];
        
        // Copy existing accounts to new array
        for (int i = 0; i < this.accounts.length; i ++)
        {
            // Check that new account ID is unique
            if (!newAcct.getID().equals(this.accounts[i].getID()))
            {
                temp[i] = this.accounts[i];
            }
            // Cancel account creation and return false if ID is in use
            else
            {
                return false;
            }
        }

        // Add new account at end of new array
        temp[temp.length - 1] = newAcct;

        // Save new array to accounts variable and return true
        this.accounts = temp;
        return true;
    }

    public Account find(String acctID) // Finds a bank account with the specified ID. Returns the account if it exists; returns null otherwise.
    {
        // Iterate through existing accounts
        for (Account acct : this.accounts)
        {
            // Return account if ID matches
            if (acct.getID().equals(acctID))
            {
                return acct;
            }
        }
        // Return null if account was not found
        return null;
    }

    public int getCount() // Returns an int value equal to the number of accounts stored in the bank.
    {
        return this.accounts.length;
    }
}