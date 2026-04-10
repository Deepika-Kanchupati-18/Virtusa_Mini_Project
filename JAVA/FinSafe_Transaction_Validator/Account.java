import java.util.ArrayList;

public class Account {

    private String holder;
    private double balance;
    private ArrayList<String> history;

    public Account(String holder, double balance) {
        this.holder = holder;
        this.balance = balance;
        this.history = new ArrayList<>();
    }

    // Central processor for spending
    public void processTransaction(double amount) throws InSufficientFundsException {

        validateAmount(amount);

        if (amount > balance) {
            throw new InSufficientFundsException("Not enough balance to complete transaction.");
        }

        balance -= amount;
        record("DEBIT", amount);

        System.out.println("Transaction Approved");
    }

    // Deposit handled separately
    public void deposit(double amount) {
        validateAmount(amount);

        balance += amount;
        record("CREDIT", amount);

        System.out.println("Amount Added Successfully");
    }

    // Validation logic
    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be greater than zero.");
        }
    }

    // Store last 5 records
    private void record(String type, double amt) {
        if (history.size() == 5) {
            history.remove(0);
        }
        history.add(type + " : " + amt);
    }

    public void printMiniStatement() {
        System.out.println("\n MINI STATEMENT");

        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String entry : history) {
                System.out.println(entry);
            }
        }
        System.out.println("Available Balance : " + balance);
    }
}
