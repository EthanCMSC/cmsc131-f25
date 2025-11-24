package projects.bank;

public class Deposit extends Transaction
{
    // Constructors
    /**
     * {@code Deposit} constructor
     * @param accountID - The ID of the {@code Account} that the {@code Deposit} should be executed on
     * @param amount - The amount of money to deposit to the target account
     */
    public Deposit(
        String accountID,
        double amount
    ) {
        super(
            accountID,
            amount
        );
    }

    // Instance methods
    /**
     * Executes the deposit. Returns {@code true} if successful; returns {@code false} otherwise.
     * @param acct - The account to execute the deposit on
     * @return {@code true} if successful
     */
    public boolean execute(Account acct, Audit audit)
    {
        if (this.validate(acct, audit))
        {
            acct.credit(this.getAmount());
            audit.recordSuccess(this, acct);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Confirms that a deposit can be executed.
     * @param acct - The account that the deposit would be executed on.
     * @return {@code true} if the deposit is safe to execute.
     */
    protected boolean validate(Account acct, Audit audit)
    {
        return true;
    }

    @Override
    public String toString()
    {
        return String.format(
            "Deposit of $%.2f to account %s",
            getAmount(), 
            getAccountID()
        );
    }

    /**
     * Returns a CSV line holding this {@code Deposit}'s data.
     * @return Eg, "wz240833,8111.00,deposit"
     */
    public String toCSV()
    {
        return String.format(
            "%s,%.2f,%s",
            this.getAccountID(),
            this.getAmount(),
            TransactionType.DEPOSIT.name().toLowerCase()
        );
    }
}