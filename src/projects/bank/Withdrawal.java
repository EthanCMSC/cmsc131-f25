package projects.bank;

public class Withdrawal extends Transaction
{
    // Constructors
    /**
     * {@code Withdrawal} constructor
     * @param accountID - The ID of the {@code Account} that the {@code Withdrawal} should be executed on
     * @param amount - The amount of money to withdraw from the target account
     */
    public Withdrawal(String accountID, double amount)
    {
        super(accountID, amount);
    }

    // Instance methods
    /**
     * Executes the withdrawal. Returns {@code true} if successful; returns {@code false} otherwise.
     * @param acct - The account to execute the withdrawal on
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean execute(Account acct)
    {
        if (acct != null
        &&  this.validate(acct)
        ) {
            acct.debit(this.getAmount());
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
     * @return {@code true} if the withdrawal is safe to execute; {@code false} otherwise.
     */
    protected boolean validate(Account acct)
    {
        return acct.getBalance() >= this.getAmount();
    }

    @Override
    public String toString()
    {
        return String.format(
            "%s,%.2f,%s",
            this.getAccountID(),
            this.getAmount(),
            TransactionType.WITHDRAWAL.name().toLowerCase()
        );
    }

    /**
     * Returns a CSV line holding this {@code Withdrawal}'s data.
     * @return Eg, "wz240833,8111.00,withdrawal"
     */
    public String toCSV()
    {
        return this.toString();
    }
}