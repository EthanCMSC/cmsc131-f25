package projects.bank;

public class Account {

    // Instance variables
    private String id;
    private String ownerName;
    private double balance;
    private AccountType accountType;

    /**
     * Account Constructor
     * @param id - The ID String that distinguishes the Account from other Account instances. Must be unique.
     * @param ownerName - The name of the account's owner.
     * @param balance - The current balance in the Account.
     * @param accountType - Whether the Account is a checking or savings account.
     */
    public Account (
        String id,
        String ownerName,
        double balance,
        AccountType accountType
    ) {
        // Ensure no null values are present
        if (id != null
        &&  ownerName != null
        &&  accountType != null
        ) {
            this.id = id;
            this.ownerName = ownerName;
            this.balance = balance;
            this.accountType = accountType;
        }
        // Throw error if null value is found
        else if (id == null)
        {
            throw new IllegalArgumentException("New Account's id value must not be null.");
        }
        else if (ownerName == null)
        {
            throw new IllegalArgumentException("New Account's ownerName value must not be null.");
        }
        else if (accountType == null)
        {
            throw new IllegalArgumentException("New Account's accountType value must not be null.");
        }
    }

    /**
     * ID Accessor
     * @return - Account ID String
     */
    public String getID()
    {
        return this.id;
    }

    /**
     * Owner Name Accessor
     * @return - Name of Account's owner
     */
    public String getOwnerName()
    {
        return this.ownerName;
    }

    /**
     * Balance Accessor
     * @return - Current Account balance
     */
    public double getBalance()
    {
        return this.balance;
    }

    /**
     * Account Type Accessor
     * @return - AccountType CHECKING or SAVINGS
     */
    public AccountType getAccountType()
    {
        return this.accountType;
    }

    /**
     * Compares two Account objects; returns true if their data is the same
     * @param other - Account to compare with
     * @return - true if Accounts' data match; false otherwise
     */
    public boolean equals(Account other)
    {
        return this.getID().equals(other.getID())
            && this.getOwnerName().equals(other.getOwnerName())
            && this.getBalance() == other.getBalance()
            && this.getAccountType() == other.getAccountType();
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.id, this.ownerName, this.balance, this.accountType);
    }
}