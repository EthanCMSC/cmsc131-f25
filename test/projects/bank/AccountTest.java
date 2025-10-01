package projects.bank;

public class AccountTest
{
    public static void main(String[] args)
    {
        // Initialize test Accounts
        Account checkingAcct = new Account(
            "JohnDoeChecking",
            "John Doe",
            1000.00,
            AccountType.CHECKING
        );
        Account savingsAcct = new Account(
            "JaneDoeSavings",
            "Jane Doe",
            1000.00,
            AccountType.SAVINGS
        );

        System.out.println(checkingAcct.getID()); // Should print "0"
        System.out.println(savingsAcct.getOwnerName()); // Should print "Jane Doe"
        System.out.println(checkingAcct.getBalance()); // Should print "4598.05"
        System.out.println(checkingAcct.getAccountType()); // Should print "CHECKING"
        System.out.println(savingsAcct.getAccountType()); // Should print "SAVINGS"
        System.out.println(checkingAcct.equals(checkingAcct)); // Should print "true"
        System.out.println(checkingAcct.equals(savingsAcct)); // Should print "false"
    }
}