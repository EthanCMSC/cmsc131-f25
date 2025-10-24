/* TODO list
 * TransactionType enum only *required* for the make method
 *     ok to remove transactionType from attributes and constructor
 *     if you remove, remember to also remove getTransactionType method 
 * your data/transactions.csv is empty, download it from blackboard or copy-paste it from the class repo
 */

 /* compliments
  * it's smart to check for amount < 0 in the constructor
  * great javadocs all around
  */
package projects.bank;

public abstract class Transaction
{
    // Instance variables
    private String accountID;
    private double amount;
    private TransactionType transactionType;

    // Constructors
    /**
     * {@code Transaction} constructor
     * @param accountID - The ID of the transaction's target account
     * @param amount - The amount of money that should be deposited or withdrawn
     * @param transactionType - Whether the transaction is a deposit or withdrawal.
     */
    protected Transaction(String accountID, double amount, TransactionType transactionType)
    {
        // Ensure no null values are present and transaction amount is not negative
        if (accountID != null
        &&  amount >= 0)
        {
            this.accountID = accountID;
            this.amount = amount;
            this.transactionType = transactionType;
        }
        else if (accountID == null)
        {
            throw new IllegalArgumentException("New Transaction's accountID value must not be null.");
        }
        else if (amount < 0)
        {
            throw new IllegalArgumentException("New Transaction's amount value must not be negative.");
        }
    }

    // Abstract methods
    /**
     * Executes the Transaction. Returns true if successful; returns false otherwise.
     * @param account - The Account to execute the Transaction on
     */
    public abstract boolean execute(Account acct);
    
    /**
     * Confirms that a transaction can be executed.
     * @param acct - The account that the transaction would be executed on.
     * @return {@code true} if the transaction is safe to execute; {@code false} otherwise.
     * @throws IllegalArgumentException if parameter is {@code null}
     */
    protected abstract boolean validate(Account acct);

    // Concrete methods
    /**
     * Transaction Account ID accessor
     * @return The ID of the transaction's target account
     */
    public String getAccountID()
    {
        return this.accountID;
    }

    /**
     * Transaction amount accessor
     * @return The amount of money being changed in the transaction
     */
    public double getAmount()
    {
        return this.amount;
    }
    
    /**
     * Transaction type accessor
     * @return Transaction's {@code TransactionType} value (should be either {@code TransactionType.DEPOSIT} or {@code TransactionType.WITHDRAWAL})
     */
    public TransactionType getTransactionType()
    {
        return this.transactionType;
    }
    
    /**
     * Factory method for constructing an {@code Transaction} object from a CSV line.
     * @param inputLine - Eg, "wz240833,8111.00,deposit"
     * @return New {@code Transaction} from supplied values.
     * @throws {@code IllegalArgumentException} if {@code inputLine} is {@code null}.
     */
    public static Transaction make(String inputLine)
    {
        if (inputLine == null)
        {
            throw new IllegalArgumentException("inputLine cannot be null");
        }
        String[] tokens = inputLine.split(",");
        String accountID = tokens[0];
        double amount = Double.valueOf(tokens[1]);
        TransactionType transactionType = TransactionType.valueOf(tokens[2].toUpperCase());
        if (transactionType.equals(TransactionType.DEPOSIT))
        {
            return new Deposit(accountID, amount);
        }
        else
        {
            return new Withdrawal(accountID, amount);
        }
    }

    @Override
    public String toString()
    {
        return String.format(
            "%s,%.2f,%s", // format double to 2 decimal places
            this.getAccountID(),
            this.getAmount(),
            this.getTransactionType().name().toLowerCase()
        );
    }

    /**
     * Returns a CSV line holding this {@code Transaction}'s data.
     * @return Eg, "wz240833,8111.00,deposit"
     */
    public String toCSV()
    {
        return this.toString();
    }
}