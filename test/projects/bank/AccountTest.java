package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest
{
    private Account account;

    @BeforeEach
    void setupAccount()
    {
        account = new SavingsAccount(
            "wz240833",
            "Anna Gomez",
            1000.00
        );
    }

    @Test
    void testAddDataValidation()
    {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new CheckingAccount(null, "name", 0.0);}
        );
        assertEquals("New Account's id value must not be null.", e.getMessage());

        e = assertThrows(
            IllegalArgumentException.class,
            () -> {new CheckingAccount("id", null, 0.0);}
        );
        assertEquals("New Account's ownerName value must not be null.", e.getMessage());
    }

    @Test
    void testCredit()
    {
        account.credit(250.00);
        
        assertEquals(
            account.getBalance(),
            1250.00
        );
    }

    @Test
    void testDebit()
    {
        account.debit(250.00);
        
        assertEquals(
            account.getBalance(),
            750.00
        );
    }

    @Test
    void testMakeThrowsOnNullInput()
    {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {Account.make(null);}
        );
        assertEquals(
            "inputLine cannot be null",
            exception.getMessage()
        );
    }

    @Test
    void testMakePreservesData()
    {
        Account account2 = Account.make(
            "wz240833,Anna Gomez,1000.00,savings"
        );
        assertEquals(
            account.getID(),
            account2.getID()
        );
        assertEquals(
            account.getOwnerName(),
            account2.getOwnerName()
        );
        assertEquals(
            account.getAccountType(),
            account2.getAccountType()
        );
        assertEquals(
            account.getBalance(),
            account2.getBalance(),
            1e-2
        );
    }

    @Test
    void testToCSV()
    {
        assertEquals(
            "wz240833,Anna Gomez,1000.00,savings",
            account.toCSV()
        );
    }
}