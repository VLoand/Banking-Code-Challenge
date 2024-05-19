import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Account> accounts;
    private List<Transaction> transactions;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFee;
    private double transactionPercentFee;

    public Bank(String name, double transactionFlatFee, double transactionPercentFee) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
        this.transactionFlatFee = transactionFlatFee;
        this.transactionPercentFee = transactionPercentFee;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountById(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null; 
    }

    public void performTransaction(Transaction transaction, boolean useFlatFee) throws Exception {
        transaction.execute(this);
        transactions.add(transaction);
        if (transaction instanceof TransferTransaction) {
            totalTransferAmount += transaction.amount;
        }
        double fee = useFlatFee ? transactionFlatFee : calculateTransactionFee(transaction.amount);
        totalTransactionFeeAmount += fee;
    }

    public double calculateTransactionFee(double amount) {
        return amount * transactionPercentFee / 100;
    }

    public void addTransactionFee(double fee) {
        totalTransactionFeeAmount += fee;
    }

    public List<Transaction> getTransactionsForAccount(int accountId) {
        List<Transaction> accountTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.originatingAccountId == accountId || transaction.resultingAccountId == accountId) {
                accountTransactions.add(transaction);
            }
        }
        return accountTransactions;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}