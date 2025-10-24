package projects.bank;

public class Withdrawal extends Transaction
{
    // Constructors
    public Withdrawal(String accountID, double amount)
    {
        super(accountID, amount, TransactionType.WITHDRAWAL);
    }

    // Instance methods
    /**
     * Executes the Withdrawal. Returns true if successful; returns false otherwise.
     * @param acct - The account to execute the Withdrawal on
     * @return {@code true} if successful; {@code false} otherwise
     * @throws IllegalArgumentException if passed value is {@code null}
     */
    public boolean execute(Account acct)
    {
        if (this.validate(acct))
        {
            return acct.debit(this.getAmount());
        }
        else
        {
            throw new IllegalArgumentException("Withdrawal.execute() method's acct parameter must not be null.");
        }
    }

    /**
     * Confirms that a withdrawal can be executed.
     * @param acct - The account that the withdrawal would be executed on.
     * @return {@code true} if the withdrawal is safe to execute; {@code false} otherwise.
     * @throws IllegalArgumentException if parameter is {@code null}
     */
    protected boolean validate(Account acct)
    {
        if (acct != null)
        {
            return acct.getBalance() >= this.getAmount();
        }
        else
        {
            throw new IllegalArgumentException("Deposit.validate() method's acct parameter must not be null.");
        }
    }
}