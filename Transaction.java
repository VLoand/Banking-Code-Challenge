public abstract class Transaction {
    protected double amount;
    protected int originatingAccountId;
    protected int resultingAccountId;
    protected String reason;

    public Transaction(double amount, int originatingAccountId, int resultingAccountId, String reason) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.reason = reason;
    }

    public abstract void execute(Bank bank) throws Exception;

    @Override
    public String toString() {
        return "Transaction from Account " + originatingAccountId + " to Account " + resultingAccountId + ", Amount: $" + String.format("%.2f", amount) + ", Reason: " + reason;
    }
}

class TransferTransaction extends Transaction {
    public TransferTransaction(double amount, int originatingAccountId, int resultingAccountId, String reason) {
        super(amount, originatingAccountId, resultingAccountId, reason);
    }

    @Override
    public void execute(Bank bank) throws Exception {
        Account fromAccount = bank.getAccountById(originatingAccountId);
        Account toAccount = bank.getAccountById(resultingAccountId);
        if (fromAccount == null || toAccount == null) {
            throw new Exception("Account not found");
        }
        if (!fromAccount.withdraw(amount)) {
            throw new Exception("Not enough funds");
        }
        toAccount.deposit(amount);
        bank.addTransactionFee(bank.calculateTransactionFee(amount));
    }
}

class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double amount, int originatingAccountId, String reason) {
        super(amount, originatingAccountId, -1, reason);
    }

    @Override
    public void execute(Bank bank) throws Exception {
        Account fromAccount = bank.getAccountById(originatingAccountId);
        if (fromAccount == null) {
            throw new Exception("Account not found");
        }
        if (!fromAccount.withdraw(amount)) {
            throw new Exception("Not enough funds");
        }
        bank.addTransactionFee(bank.calculateTransactionFee(amount));
    }
}

class DepositTransaction extends Transaction {
    public DepositTransaction(double amount, int resultingAccountId, String reason) {
        super(amount, -1, resultingAccountId, reason);
    }

    @Override
    public void execute(Bank bank) throws Exception {
        Account toAccount = bank.getAccountById(resultingAccountId);
        if (toAccount == null) {
            throw new Exception("Account not found");
        }
        toAccount.deposit(amount);
        bank.addTransactionFee(bank.calculateTransactionFee(amount));
    }
}