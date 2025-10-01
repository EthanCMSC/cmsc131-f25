package projects.bank;

public class Account {

    // Instance variables
    private final String id;
    private final String ownerName;
    private double balance;
    private final AccountType accountType;

    // Constructors TODO add javadoc
    public Account (
        String id,
        String ownerName,
        double balance,
        AccountType accountType
    ) {
        // Ensure no null values are present
        // TODO better to check one at a time and throw a distinct error for each
        if (id != null
        &&  ownerName != null
        &&  balance != null  // remove this line
        &&  accountType != null
        ) {
            this.id = id;
            this.ownerName = ownerName;
            this.balance = balance;
            this.accountType = accountType;
        }
        // Throw error if null value is found
        else
        {
            throw new IllegalArgumentException("New Account must not have any null values.");
        }
    }

    // Returns the ID of the account.
    public String getID()
    {
        return this.id;
    }

    // Returns the name of the account's owner.
    public String getOwnerName()
    {
        return this.ownerName;
    }

    // Returns the balance in the account.
    public double getBalance()
    {
        return this.balance;
    }

    // Returns the Account's type (checking or savings).
    public AccountType getAccountType()
    {
        return this.accountType;
    }

    // Returns true if the Account's data matches that of the Account passed
    // Consider also overriding hashCode
    public boolean equals(Account other)
    {
        return this.getID().equals(other.getID())
            && this.getOwnerName().equals(other.getOwnerName())
            && this.getBalance() == other.getBalance()
            && this.getAccountType() == other.getAccountType();
    }
}