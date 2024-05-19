import static org.junit.Assert.*;
import org.junit.Test;

public class TransactionTest {
    
    @Test
    public void testTransferTransactionExecute() {
        Bank bank = new Bank("Test Bank", 10.0, 0.05);
        Account fromAccount = new Account(1, "Sender", 100.0);
        Account toAccount = new Account(2, "Receiver", 50.0);
        bank.addAccount(fromAccount);
        bank.addAccount(toAccount);
        
        double amount = 20.0;
        Transaction transaction = new TransferTransaction(amount, fromAccount.getAccountId(), toAccount.getAccountId(), "Test transfer");
        try {
            transaction.execute(bank);
            assertEquals(80.0, fromAccount.getBalance(), 0.001);
            assertEquals(70.0, toAccount.getBalance(), 0.001);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
}
