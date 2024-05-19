import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter flat fee amount: ");
        double flatFee = scanner.nextDouble();
        System.out.print("Enter percent fee amount: ");
        double percentFee = scanner.nextDouble();

        Bank bank = new Bank(bankName, flatFee, percentFee);

        while (true) {
            System.out.println("\nBank System Menu:");
            System.out.println("1. Create an account");
            System.out.println("2. Perform a transaction");
            System.out.println("3. Withdraw money");
            System.out.println("4. Deposit money");
            System.out.println("5. View account transactions");
            System.out.println("6. Check account balance");
            System.out.println("7. List all accounts");
            System.out.println("8. View total transaction fee amount");
            System.out.println("9. View total transfer amount");
            System.out.println("10. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter account ID: ");
                        int accountId = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Enter user name: ");
                        String userName = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double balance = scanner.nextDouble();
                        bank.addAccount(new Account(accountId, userName, balance));
                        System.out.println("Account created successfully.");
                        break;
                    case 2:
                        System.out.print("Enter originating account ID: ");
                        int originatingAccountId = scanner.nextInt();
                        System.out.print("Enter resulting account ID: ");
                        int resultingAccountId = scanner.nextInt();
                        System.out.print("Enter amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); 
                        System.out.print("Enter reason: ");
                        String reason = scanner.nextLine();
                        System.out.print("Use flat fee? (true/false): ");
                        boolean useFlatFee = scanner.nextBoolean();
                        bank.performTransaction(new TransferTransaction(amount, originatingAccountId, resultingAccountId, reason), useFlatFee);
                        System.out.println("Transaction performed successfully.");
                        break;
                    case 3:
                        System.out.print("Enter account ID: ");
                        int withdrawAccountId = scanner.nextInt();
                        System.out.print("Enter amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); 
                        System.out.print("Enter reason: ");
                        String withdrawReason = scanner.nextLine();
                        System.out.print("Use flat fee? (true/false): ");
                        boolean withdrawUseFlatFee = scanner.nextBoolean();
                        bank.performTransaction(new WithdrawalTransaction(withdrawAmount, withdrawAccountId, withdrawReason), withdrawUseFlatFee);
                        System.out.println("Withdrawal performed successfully.");
                        break;
                    case 4:
                        System.out.print("Enter account ID: ");
                        int depositAccountId = scanner.nextInt();
                        System.out.print("Enter amount: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); 
                        System.out.print("Enter reason: ");
                        String depositReason = scanner.nextLine();
                        System.out.print("Use flat fee? (true/false): ");
                        boolean depositUseFlatFee = scanner.nextBoolean();
                        bank.performTransaction(new DepositTransaction(depositAmount, depositAccountId, depositReason), depositUseFlatFee);
                        System.out.println("Deposit performed successfully.");
                        break;
                    case 5:
                        System.out.print("Enter account ID: ");
                        int viewAccountId = scanner.nextInt();
                        for (Transaction transaction : bank.getTransactionsForAccount(viewAccountId)) {
                            System.out.println(transaction);
                        }
                        break;
                    case 6:
                        System.out.print("Enter account ID: ");
                        int balanceAccountId = scanner.nextInt();
                        Account account = bank.getAccountById(balanceAccountId);
                        if (account != null) {
                            System.out.println("Balance: $" + String.format("%.2f", account.getBalance()));
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 7:
                        for (Account acc : bank.getAccounts()) {
                            System.out.println(acc);
                        }
                        break;
                    case 8:
                        System.out.println("Total transaction fee amount: $" + String.format("%.2f", bank.getTotalTransactionFeeAmount()));
                        break;
                    case 9:
                        System.out.println("Total transfer amount: $" + String.format("%.2f", bank.getTotalTransferAmount()));
                        break;
                    case 10:
                        System.out.println("Exiting the system.");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}