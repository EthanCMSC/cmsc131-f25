package examples.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest
{
    void testSameAs()
    {
        Account acct1 = new Account (
            "id1",
            "Al Davis",
            0,
            AccountType.CHECKING
        );
        Account acct2 = new Account (
            "id1",
            "Al Davis",
            0.0,
            AccountType.CHECKING
        );
        Account acct3 = new Account (
            "id2",
            "Betty White",
            1000.0,
            AccountType.SAVINGS
        );

        assertEquals(
            true,
            acct1.equals(acct1)
        );
        assertEquals(
            true,
            acct1.equals(acct2)
        );
        assertEquals(
            true,
            acct1.equals(acct3)
        );
    }
}
