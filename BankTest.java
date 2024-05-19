import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank("Test Bank", 10.0, 0.05);
    }

    @Test
    public void testAddAccount() {
        Account account = new Account(1, "John Doe", 100.0);
        bank.addAccount(account);
        assertEquals(account, bank.getAccountById(1));
    }

    @Test
    public void testPerformTransaction() {
        Account fromAccount = new Account(1, "Sender", 100.0);
        Account toAccount = new Account(2, "Receiver", 50.0);
        bank.addAccount(fromAccount);
        bank.addAccount(toAccount);

        try {
            bank.performTransaction(new TransferTransaction(20.0, 1, 2, "Test transfer"));
            assertEquals(80.0, fromAccount.getBalance(), 0.001);
            assertEquals(70.0, toAccount.getBalance(), 0.001);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
}
