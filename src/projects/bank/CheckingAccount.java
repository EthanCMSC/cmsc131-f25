package projects.bank;

/** TODO list
 * remove account type from call to Account class constructor
 * override abstract getType method from Account class
 */

public class CheckingAccount extends Account
{
    // Constructors
    /**
     * CheckingAccount constructor
     * @param id - The ID String that distinguishes the Account from other Account instances. Must be unique.
     * @param ownerName - The name of the account's owner.
     * @param balance - The current balance in the Account.
     */
    public CheckingAccount (
        String id,
        String ownerName,
        double balance
        ) {
            super(id, ownerName, balance, AccountType.CHECKING);
        }
}