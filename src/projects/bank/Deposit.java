/** TODO list
 * see comments for Withdrawal class
 */
package projects.bank;

public class Deposit extends Transaction
{
    // Constructors
    /**
     * Deposit constructor
     * @param accountID - The ID of the Account that the Deposit should be executed on
     * @param amount - The amount of money to deposit to the target Account
     */
    public Deposit(String accountID, double amount)
    {
        super(accountID, amount, TransactionType.DEPOSIT);
    }

    // Instance methods
    /**
     * Executes the deposit. Returns {@code true} if successful; returns {@code false} otherwise.
     * @param acct - The account to execute the deposit on
     * @return {@code true} if successful
     * @throws IllegalArgumentException if passed value is {@code null}
     */
    public boolean execute(Account acct)
    {
        if (acct != null)
        {
            acct.credit(this.getAmount());
            return true;
        }
        else
        {
            throw new IllegalArgumentException("Deposit.execute() method's acct parameter must not be null.");
        }
    }

    /**
     * Confirms that a deposit can be executed.
     * @param acct - The account that the deposit would be executed on.
     * @return {@code true} if the deposit is safe to execute.
     * @throws IllegalArgumentException if parameter is {@code null}
     */
    protected boolean validate(Account acct)
    {
        if (acct != null)
        {
            return true;
        }
        else
        {
            throw new IllegalArgumentException("Deposit.validate() method's acct parameter must not be null.");
        }
    }
}