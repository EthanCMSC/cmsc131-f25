package examples.account;

public class Account {

    private final String accountID;
    private final String ownerName;
    private final double accountBalance;
    private final AccountType accountType;

    public Account (
        String id,
        String name,
        double balance,
        AccountType type
    ) {
        accountID = id;
        ownerName = name;
        accountBalance = balance;
        accountType = type;
    }

    public String getAccountID()
    {
        return this.accountID;
    }

    public String getOwnerName()
    {
        return this.ownerName;
    }

    public double getAccountBalance()
    {
        return this.accountBalance;
    }

    public AccountType getAccountType()
    {
        return this.accountType;
    }

    public boolean equals(Account other)
    {
        return this.getAccountID().equals(other.getAccountID())
            && this.getOwnerName().equals(other.getOwnerName())
            && this.getAccountBalance() == other.getAccountBalance()
            && this.getAccountType() == other.getAccountType();
    }
}