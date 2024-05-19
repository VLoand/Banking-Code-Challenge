import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BankSystemIntegrationTest {
    private BankSystem bankSystem;

    @Before
    public void setUp() {
        // Initialize the BankSystem with a test bank
        Bank bank = new Bank("Test Bank", 10.0, 0.05);
        bankSystem = new BankSystem(bank);
    }

    @Test
    public void testBankSystemIntegration() {
        // Test scenario: Create accounts, perform transactions, and verify results

        // Create accounts
        bankSystem.createAccount(1, "Alice", 100.0);
        bankSystem.createAccount(2, "Bob", 50.0);

        // Perform transactions
        bankSystem.performTransfer(1, 2, 20.0);
        bankSystem.performDeposit(1, 30.0);
        bankSystem.performWithdrawal(2, 10.0);

        // Verify account details
        assertEquals(110.0, bankSystem.getAccountBalance(1), 0.001);
        assertEquals(40.0, bankSystem.getAccountBalance(2), 0.001);

        // Verify bank statistics
        assertEquals(2, bankSystem.getNumberOfAccounts());
        assertEquals(3, bankSystem.getNumberOfTransactions());
        assertEquals(40.0, bankSystem.getTotalTransactionFeeAmount(), 0.001);
        assertEquals(50.0, bankSystem.getTotalTransferAmount(), 0.001);
    }
}
