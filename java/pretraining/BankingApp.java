import java.util.Scanner;
import java.sql.*;

public class BankingApp {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Create Account\n2. login_account\n3. Exit");
            int choice = sc.nextInt();
            if (choice == 1) registar_account();
            else if (choice == 2) login_account();
            else System.exit(0);
        }
    }

    private static void registar_account() {
        System.out.print("Acc Number: "); String acc = sc.next();
        System.out.print("Password: "); String pass = sc.next();
        System.out.print("Type (Savings/Current): "); String type = sc.next();
        System.out.print("Initial Deposit: "); double dep = sc.nextDouble();

        String sql = "INSERT INTO accounts (account_number, password, account_type, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, acc);
            pstmt.setString(2, pass);
            pstmt.setString(3, type);
            pstmt.setDouble(4, dep);
            pstmt.executeUpdate();
            System.out.println("Success!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void login_account() {
        System.out.print("Acc Number: "); String accNum = sc.next();
        System.out.print("Password: "); String pass = sc.next();

        String sql = "SELECT * FROM accounts WHERE account_number = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accNum);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Account account = new Account(rs.getString("account_number"), rs.getDouble("balance"));
                userMenu(account);
            } else {
                System.out.println("Login Failed.");
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void userMenu(Account acc) {
        while (true) {
            System.out.println("\n1. Balance 2. Deposit 3. Withdraw 4. History 5. Logout");
            int c = sc.nextInt();
            if (c == 1) System.out.println("Balance: ₹" + acc.Balance());
            else if (c == 2) { System.out.print("Amt: "); acc.deposit(sc.nextDouble()); }
            else if (c == 3) { 
                System.out.print("Amt: "); 
                if (!acc.withdraw(sc.nextDouble())) System.out.println("Funds are insufficient");
            }
            else if (c == 4) acc.showHistory();
            else break;
        }
    }
}