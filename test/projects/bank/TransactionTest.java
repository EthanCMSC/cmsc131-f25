/** TODO list
 * testAddDataValidation
 *     only need to test `new Deposit`
 *     because data validation logic lives in Transaction
 * add testValidate
 *     check validation for Deposit and Withdrawal
 *         because they each define their own validate method
 *         (compare to my comment about data validation in Transaction superclass)
 * add testExecute
 *     check execution for Deposit and Withdrawal
 *         the logic is similar to testCredit and testDebit
 */
package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest
{
    private Deposit dpst;
    private Withdrawal wtdl;

    @BeforeEach
    void setup()
    {
        dpst = new Deposit(
            "id0",
            0.50
        );
        wtdl = new Withdrawal(
            "id0",
            0.50
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
        
        e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Withdrawal(null, 0.0);}
        );
        assertEquals("New Transaction's accountID value must not be null.", e.getMessage());
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
            "id0,0.50,deposit"
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
        assertEquals(
            dpst.getTransactionType(),
            dpst2.getTransactionType()
        );
    }

    @Test
    void testToCSV()
    {
        assertEquals(
            "id0,0.50,deposit",
            dpst.toCSV()
        );
    }
}