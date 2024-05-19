import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    private Account account;

    @Before
    public void setUp() {
        account = new Account(1, "John Doe", 100.0);
    }

    @Test
    public void testWithdrawSufficientFunds() {
        assertTrue(account.withdraw(50.0));
        assertEquals(50.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        assertFalse(account.withdraw(150.0));
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDeposit() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.001);
    }
}
