package projects.bank;

public class Withdrawal extends Transaction
{
    // Constructors
    /**
     * {@code Withdrawal} constructor
     * @param accountID - The ID of the {@code Account} that the {@code Withdrawal} should be executed on
     * @param amount - The amount of money to withdraw from the target account
     */
    public Withdrawal(
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
     * Executes the withdrawal. Returns {@code true} if successful; returns {@code false} otherwise.
     * @param acct - The account to execute the withdrawal on
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean execute(Account acct, Audit audit)
    {
        if (acct != null
        &&  this.validate(acct, audit)
        ) {
            acct.debit(this.getAmount());
            audit.recordSuccess(this, acct);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Confirms that a withdrawal can be executed.
     * @param acct - The account that the withdrawal would be executed on.
     * @param audit - The {@code Audit} object stored in the target account's bank.
     * @return {@code true} if the withdrawal is safe to execute; {@code false} otherwise.
     */
    protected boolean validate(Account acct, Audit audit)
    {
        if (acct.getBalance() >= this.getAmount())
        {
            return true;
        }
        else
        {
            audit.recordNSF(this, acct);
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "Deposit of $" + String.format("%.2f", this.getAmount()) + " to account " + this.getAccountID();
    }

    /**
     * Returns a CSV line holding this {@code Withdrawal}'s data.
     * @return Eg, "wz240833,8111.00,withdrawal"
     */
    public String toCSV()
    {
        return String.format(
            "%s,%.2f,%s",
            this.getAccountID(),
            this.getAmount(),
            TransactionType.WITHDRAWAL.name().toLowerCase()
        );
    }
}