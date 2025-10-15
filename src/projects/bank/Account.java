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
     * Factory method for constructing an Account object from a CSV line.
     * @param inputLine Eg, "wz240833,Anna Gomez,8111.00,savings"
     * @return - new Account from supplied values.
     * @throws {@code IllegalArgumentException} if null {@code input} is null.
     */
    public static Account make(String inputLine)
    {
        if (inputLine == null)
        {
            throw new IllegalArgumentException("inputLine cannot be null");
        }
        String[] tokens = inputLine.split(",");
        String id = tokens[0];
        String ownerName = tokens[1];
        double balance = (double) Double.valueOf(tokens[2]);
        AccountType type = AccountType.valueOf(tokens[3].toUpperCase());
        return new Account(id, ownerName, balance, type);
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


    @Override 
    public String toString()
    {
        return String.format(
            "%s,%s,%.2f,%s", // format double to 2 decimal places
            getID(),
            getOwnerName(),
            getBalance(),
            getAccountType().name().toLowerCase()
        );
    }

    /**
     * CSV line holding this account's data.
     * @return Eg, "wz240833,Anna Gomez,8111.00,savings"
     */
    public String toCSV()
    {
        return this.toString();
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
    public int hashCode()
    {
        return java.util.Objects.hash(this.id, this.ownerName, this.balance, this.accountType);
    }
}