package projects.bank;

public class Bank
{
    // Instance variables
    private Account[] accounts;
    private int numberOfAccounts;

    // Constructors
    public Bank()
    {
        this.accounts = new Account[100];
        this.numberOfAccounts = 0;
    }

    // Instance methods
    /**
     * Adds a new account to bank. New account's ID must be unique. Returns true if successful; returns false otherwise.
     * @param newAcct - The new Account to add
     * @return - true if account was successfully added; false otherwise
     */
    public boolean add(Account newAcct)
    {
        // Check that passed account is not null
        if (newAcct != null)
        {
            // Check if account ID is unique
            if (this.find(newAcct.getID()) == -1)
            {
                int arrSizeIncrement = 100; // Increase size of accounts array by this value every overflow

                // Copy accounts to larger array if overflow occurs
                if (numberOfAccounts % arrSizeIncrement != 0)
                {
                    Account[] temp = new Account[this.accounts.length + arrSizeIncrement];
                    for (int i = 0; i < this.accounts.length; i ++)
                    {
                        temp[i] = this.accounts[i];
                    }
                    this.accounts = temp;
                }
                // Add account to array
                accounts[numberOfAccounts] = newAcct;
                numberOfAccounts ++;
                return true;
            }
            else
            {
                // Return false if account ID is not unique
                return false;
            }
        }
        else
        {
            throw new IllegalArgumentException("New Account must not be null.");
        }
    }

    /**
     * Returns the index of a specified account by its ID if it exists.
     * @param acctID - The ID of the account to search for
     * @return - Account's index in accounts array if it exists; -1 otherwise
     */
    public int find(String acctID)
    {
        if (acctID != null)
        {
            // Iterate through existing accounts
            for (int i = 0; i < this.numberOfAccounts; i ++)
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
        else
        {
            throw new IllegalArgumentException("Target Account's ID must not be null.");
        }
    }

    /**
     * Returns the number of accounts in the bank
     * @return - int equal to number of accounts in bank
     */
    public int getCount()
    {
        return this.numberOfAccounts;
    }

    /**
     * Compares two Bank objects; returns true if their data is the same
     * @param other - Bank to compare with
     * @return - true if Banks' data match; false otherwise
     */
    public boolean equals(Bank other)
    {
        if (this.getCount() == other.getCount())
        {
            for (int i = 0; i < this.accounts.length; i ++)
            {
                if (this.accounts[i] != null
                &&  !this.accounts[i].equals(other.accounts[i]))
                {
                    // Return false if data does not match
                    return false;
                }
            }
            // Return true if data matches
            return true;
        }
        // Return false if data does not match
        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.accounts);
    }
}