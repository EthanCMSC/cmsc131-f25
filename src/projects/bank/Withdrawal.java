/** TODO list
 * remove transaction type from call to Transaction constructor
 * unnecessary to check whether acct is null in validate. so, remove that logic and just return acct.getbalance() >= getAmount()
 * don't have execute throw an exception. we don't want the simulation to crash if the transaction is blocked from executing
 */

 /** compliments
  * you put the validation logic into the execute method. because of this, your Withdrawal method has a "conscience" in some sense. that's a nice design choice, andit'll also make  your bank's processTransactions code simpler.
  */
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
        if (acct != null)
        {
            if (this.validate(acct))
            {
                acct.debit(this.getAmount());
                return true;
            }
            else
            {
                return false;
            }
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