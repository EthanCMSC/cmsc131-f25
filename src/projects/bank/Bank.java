package projects.bank;

public class Bank
{
    // Instance variables
    private Account[] accounts;

    // Constructors
    public Bank()
    {
        this.accounts = new Account[100];
    }

    // Instance methods
    /**
     * Adds a new account to bank. New account's ID must be unique. Returns true if successful; returns false otherwise.
     * @param newAcct - The new Account to add
     * @return - true if account was successfully added; false otherwise
     */
    public boolean add(Account newAcct)
    {
        // Check if account ID is unique
        if (this.find(newAcct.getID()) == -1)
        {
            for (int i = 0; i < accounts.length; i ++)
            {
                // Add account to current array if free slot exists
                if (accounts[i] == null)
                {
                    accounts[i] = newAcct;
                    return true;
                }
            }
            // Copy accounts to larger array and add new account there if overflow occurs
            Account[] temp = new Account[this.accounts.length + 100];
            for (int i = 0; i < this.accounts.length; i ++)
            {
                temp[i] = this.accounts[i];
            }
            temp[this.accounts.length] = newAcct;
            this.accounts = temp;
            return true;
        }
        else
        {
            // Return false if account ID is not unique
            return false;
        }
    }

    /**
     * Returns the index of a specified account by its ID if it exists.
     * @param acctID - The ID of the account to search for
     * @return - Account's index in accounts array if it exists; -1 otherwise
     */
    public int find(String acctID)
    {
        // Iterate through existing accounts
        for (int i = 0; i < this.accounts.length; i ++)
        {
            // Return account if ID matches
            if (this.accounts[i] != null
            &&  this.accounts[i].getID().equals(acctID))
            {
                return i;
            }
        }
        // Return -1 if account was not found
        return -1;
    }

    /**
     * Returns the number of accounts in the bank
     * @return - int equal to number of accounts in bank
     */
    public int getCount()
    {
        for (int i = 0; i < this.accounts.length; i ++)
        {
            if (this.accounts[i] == null)
            {
                return i;
            }
        }

        return this.accounts.length;
    }
}