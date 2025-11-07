/** TODO / comments
 * 
 * testAddDataValidation
 * be sure to also test validation of amount
 */
package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest
{
    private Deposit dpst;
    private Withdrawal wtdl;
    private Audit audit;
    private CheckingAccount acct1;
    private CheckingAccount acct2;

    @BeforeEach
    void setup()
    {
        dpst = new Deposit(
            "id0",
            250.00
        );

        wtdl = new Withdrawal(
            "id0",
            250.00
        );

        try
        {
            audit = new Audit("test/projects/bank/audittest.log");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        acct1 = new CheckingAccount(
            "id0",
            "Owner Name",
            1000.00
        );

        acct2 = new CheckingAccount(
            "id1",
            "Owner Name",
            100.00
        );
    }

    @Test
    void testAddDataValidation()
    {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Deposit(null, 0.0);}
        );
        assertEquals("New Transaction's accountID value must not be null.", e.getMessage());
    }

    @Test
    void testValidate()
    {
        assertEquals(
            dpst.validate(acct1, audit),
            true
        );
        assertEquals(
            wtdl.validate(acct1, audit),
            true
        );
        assertEquals(
            wtdl.validate(acct2, audit),
            false
        );
    }

    @Test
    void testExecute()
    {
        assertEquals(
            dpst.execute(acct1, audit),
            true
        );
        assertEquals(
            acct1.getBalance(),
            1250.00
        );

        assertEquals(
            wtdl.execute(acct1, audit),
            true
        );
        assertEquals(
            acct1.getBalance(),
            1000.00
        );

        assertEquals(
            wtdl.execute(acct2, audit),
            false
        );
        assertEquals(
            acct2.getBalance(),
            100.00
        );
    }

    @Test
    void testMakeThrowsOnNullInput()
    {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {Transaction.make(null);}
        );
        assertEquals(
            "inputLine cannot be null",
            e.getMessage()
        );
    }

    @Test
    void testMakePreservesData()
    {
        Transaction dpst2 = Transaction.make(
            "id0,250.00,deposit"
        );
        assertEquals(
            dpst.getAccountID(),
            dpst2.getAccountID()
        );
        assertEquals(
            dpst.getAmount(),
            dpst2.getAmount(),
            1e-2
        );
    }

    @Test
    void testToCSV()
    {
        assertEquals(
            "id0,250.00,deposit",
            dpst.toCSV()
        );
    }
}