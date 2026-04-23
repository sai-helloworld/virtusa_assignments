import java.sql.*;

public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String AccountNumber() { return accountNumber; }
    public double Balance() { return balance; }

    public void deposit(double amount) {
        String updateSQL = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            recordTransaction("Deposit", amount);
            this.balance += amount;
            System.out.println("Deposit Successful!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;

        String updateSQL = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            recordTransaction("Withdrawal", amount);
            this.balance -= amount;
            return true;
        } catch (SQLException e) { 
            e.printStackTrace();
            return false;
        }
    }

    private void recordTransaction(String type, double amount) {
        String logSQL = "INSERT INTO transactions (account_number, type, amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(logSQL)) {
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, type);
            pstmt.setDouble(3, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void showHistory() {
        String query = "SELECT * FROM transactions WHERE account_number = ? ORDER BY timestamp DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Transaction History ---");
            while (rs.next()) {
                System.out.printf("[%s] %s: ₹%.2f%n", rs.getTimestamp("timestamp"), rs.getString("type"), rs.getDouble("amount"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}