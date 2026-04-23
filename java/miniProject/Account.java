import java.util.ArrayList;

public class Account {
    private String accountHolder;
    private ArrayList<Double> history;
    private double balance;

    public Account(String name, double initialBalance) {
        this.accountHolder = name;
        this.balance = initialBalance;
        this.history = new ArrayList<>();
    } 
    public void processTransaction(double amount) throws InSufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("The transaction amount should be greater than zero.");
        }

        if (amount > balance) {
            throw new InSufficientFundsException("Overdraft Alert: Insufficient funds for the transaction to perform.");
        }

        balance -= amount;
        recordActivity(-amount);
        System.out.println("Payment is successfull.The Remaining balance: $" + balance);
    }
    
    public void addFunds(double amount) {
        if (amount > 0) {
            balance += amount;
            recordActivity(amount);
            System.out.println("Funds added successfully.");
        }
    }
     
    private void recordActivity(double amount) {
        history.add(amount);
        if (history.size() > 5) {
            history.remove(0); 
        }
    }

    public void printMiniStatement() {
        System.out.println("\n..................................................");
        System.out.println("\n Mini Statement");
        System.out.println("\n--- Activity of " + accountHolder + " ---");
        System.out.println("Current Balance: $" + balance);
        if (history.isEmpty()) {
            System.out.println("No recent transactions.");
        } else {
            for (Double txn : history) {
                String label = (txn > 0) ? "[CREDIT]" : "[DEBIT]";
                System.out.println(label + " : $" + Math.abs(txn));
            }
        }
        System.out.println("..................................................\n");
    }
}
