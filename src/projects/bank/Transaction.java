package projects.bank;

public abstract class Transaction
{
    // Instance variables
    private String accountID;
    private double amount;
    
    // Constructors
    /**
     * {@code Transaction} constructor
     * @param accountID - The ID of the transaction's target account
     * @param amount - The amount of money that should be deposited or withdrawn
     */
    protected Transaction(
        String accountID,
        double amount
    ) {
        // Ensure no null values are present and transaction amount is not negative
        if (accountID != null &&  amount >= 0)
        {
            this.accountID = accountID;
            this.amount = amount;
        }
        else if (accountID == null)
        {
            throw new IllegalArgumentException(
                "New Transaction's accountID value must not be null."
            );
        }
        else if (amount < 0)
        {
            throw new IllegalArgumentException(
                "New Transaction's amount value must not be negative."
            );
        }
    }

    // Abstract methods
    /**
     * Executes the Transaction. Returns true if successful; returns false otherwise.
     * @param account - The Account to execute the Transaction on
     */
    public abstract boolean execute(Account acct, Audit audit);

    /**
     * Returns a CSV line holding the {@code Transaction}'s data.
     * @return Eg, "wz240833,8111.00,deposit"
     */
    public abstract String toCSV();
    
    /**
     * Confirms that a transaction can be executed.
     * @param acct - The account that the transaction would be executed on.
     * @return {@code true} if the transaction is safe to execute; {@code false} otherwise.
     * @throws IllegalArgumentException if parameter is {@code null}
     */
    protected abstract boolean validate(Account acct, Audit audit);

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
        TransactionType transactionType = TransactionType.valueOf(
            tokens[2].toUpperCase()
        );
        if (transactionType.equals(TransactionType.DEPOSIT))
        {
            return new Deposit(accountID, amount);
        }
        else
        {
            return new Withdrawal(accountID, amount);
        }
    }
}