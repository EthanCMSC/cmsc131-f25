package projects.bank;

public abstract class Account
{
    // Instance variables
    private String id;
    private String ownerName;
    private double balance;

    // Constructors
    /**
     * {@code Account} constructor
     * @param id - The ID String that distinguishes the account from other {@code Account} instances. Must be unique.
     * @param ownerName - The name of the account's owner.
     * @param balance - The current balance in the account.
     */
    public Account (
        String id,
        String ownerName,
        double balance
    ) {
        // Ensure no null values are present
        if (id != null
        &&  ownerName != null
        ) {
            this.id = id;
            this.ownerName = ownerName;
            this.balance = balance;
        }
        // Throw error if null value is found
        else if (id == null)
        {
            throw new IllegalArgumentException(
                "New Account's id value must not be null."
            );
        }
        else if (ownerName == null)
        {
            throw new IllegalArgumentException(
                "New Account's ownerName value must not be null."
            );
        }
    }
    
    // Abstract methods
    /**
     * Account type accessor
     * @return Account's {@code AccountType} value (should be either {@code AccountType.CHECKING} or {@code AccountType.SAVINGS})
     */
    public abstract AccountType getAccountType();
    
    // Concrete methods
    /**
     * ID accessor
     * @return Account's ID String
     */
    public String getID()
    {
        return this.id;
    }

    /**
     * Owner name accessor
     * @return Name of Account's owner
     */
    public String getOwnerName()
    {
        return this.ownerName;
    }

    /**
     * Balance accessor
     * @return Account's current balance
     */
    public double getBalance()
    {
        return this.balance;
    }

    /**
     * Adds a sum of money to account balance. Returns {@code true} if successful.
     * @param amount - Amount of money to credit to account.
     * @return {@code true} if successful.
     */
    public boolean credit(double amount)
    {
        this.balance += checkFormat(amount);
        return true;
    }

    /**
     * Removes a sum of money from account balance. Returns {@code true} if successful.
     * @param amount - Amount of money to debit from account.
     * @return {@code true} if successful.
     */
    public boolean debit(double amount)
    {
        this.balance -= checkFormat(amount);
        return true;
    }

    /**
     * Formats a {@code double} to at most two decimal places.
     * @param value - The {@code double} value to format.
     * @return Passed {@code double} value, formatted to at most two decimal places.
     */
    protected double checkFormat(double value)
    {
        String str = String.format("%.2f", value);
        return Double.valueOf(str);
    }

    @Override 
    public String toString()
    {
        String accountTypeStr;
        if (this.getAccountType() == AccountType.CHECKING)
        {
            accountTypeStr = "checking";
        }
        else
        {
            accountTypeStr = "savings";
        }

        return String.format(
            "%s,%s,%.2f,%s",
            this.getID(),
            this.getOwnerName(),
            this.getBalance(),
            accountTypeStr
        );
    }

    /**
     * Returns a CSV line holding this {@code Account}'s data.
     * @return Eg, "wz240833,Anna Gomez,8111.00,savings"
     */
    public String toCSV()
    {
        return this.toString();
    }

    /**
     * Compares two Account objects; returns true if their data is the same
     * @param other - Account to compare with
     * @return {@code true} if Accounts' data match; {@code false} otherwise
     */
    public boolean equals(Account other)
    {
        return this.getID().equals(other.getID())
            && this.getOwnerName().equals(other.getOwnerName())
            && this.getBalance() == other.getBalance();
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.id, this.ownerName, this.balance);
    }

    /**
     * Factory method for constructing an {@code Account} object from a CSV line.
     * @param inputLine - Eg, "wz240833,Anna Gomez,8111.00,savings"
     * @return New {@code Account} from supplied values.
     * @throws {@code IllegalArgumentException} if {@code inputLine} is {@code null}.
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
        if (type == AccountType.CHECKING)
        {
            return new CheckingAccount(id, ownerName, balance);
        }
        else
        {
            return new SavingsAccount(id, ownerName, balance);
        }
    }
}