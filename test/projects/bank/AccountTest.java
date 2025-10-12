package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    void testDataValidation()
    {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account(null, "name", 0.0, AccountType.CHECKING);}
        );
        assertEquals("New Account's id value must not be null.", e.getMessage());

        e = assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Account("id", null, 0.0, AccountType.CHECKING);}
        );
        assertEquals("New Account's ownerName value must not be null.", e.getMessage());

        e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account("id", "Owner Name", 0.0, null);}
        );
        assertEquals("New Account's accountType value must not be null.", e.getMessage());
    }
}