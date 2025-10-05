package projects.bank;

public class BankTest
{
    public static void main(String[] args)
    {
        // Create Bank object
        Bank bank = new Bank();
        
        // Test add() method (success w/o overflow)
        System.out.println(bank.add(new Account("JohnDoeChecking", "John Doe", 1000.00, AccountType.CHECKING))); // Should print "true"
        System.out.println(bank.add(new Account("JaneDoeSavings", "Jane Doe", 1000.00, AccountType.SAVINGS))); // Should print "true"
        
        // Test add() method (failure)
        System.out.println(bank.add(new Account("JohnDoeChecking", "Fake Name", 1000.00, AccountType.CHECKING))); // Should print "false"
        
        // Test find() method (success)
        System.out.println(bank.find("JohnDoeChecking")); // Should print "0"
        System.out.println(bank.find("JaneDoeSavings")); // Should print "1"
        
        // Test add() method (success w/ overflow)
        for (int i = 0; i < 98; i ++)
        {
            bank.add(new Account("placeholder" + i, "placeholder", 0.00, AccountType.CHECKING)); // Add placeholder accounts until there are 100 accounts in the bank
        }
        System.out.println(bank.add(new Account("overflowtest", "Overflow test", 0.00, AccountType.CHECKING))); // Should print "true"

        // Test find() method (failure)
        System.out.println(bank.find("JaneDoeChecking")); // Should print "-1"
        System.out.println(bank.find("JohnDoeSavings")); // Should print "-1"
        
        // Test getCount() method
        System.out.println(bank.getCount()); // Should print "101"
        
        // Test equals() method
        Bank different = new Bank();
        System.out.println(bank.equals(bank)); // Should print "true"
        System.out.println(bank.equals(different)); // Should print "false"
    }
}