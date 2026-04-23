import java.util.Scanner;

public class FinSafe {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);

System.out.println("=== Account Wallet Setup ===");
System.out.print("Enter account holder's name: ");
String userName = input.nextLine();

Account Account = new Account(userName, 0.0);
boolean isActive_key= true;

while (isActive_key){
System.out.println("Select Action: (1) Deposit (2) Spend (3) History (4) Exit");
String key = input.nextLine();

try {
switch (key) {
case "1":
System.out.print("Enter deposit amount: ");
double a = Double.parseDouble(input.nextLine());
Account.addFunds(a);
break;
case "2":
System.out.print("Enter spending amount: ");
double b = Double.parseDouble(input.nextLine());
Account.processTransaction(b);
break;
case "3":
Account.printMiniStatement();
break;
case "4":
isActive_key= false;
System.out.println("Closing FinSafe.Have a safe day!");
break;
default:
System.out.println("Menu error.Please pick 1, 2, 3, or 4.");
}
} catch (InSufficientFundsException | IllegalArgumentException e) {
System.out.println("NOTICE: " + e.getMessage());
} catch (Exception e) {
System.out.println("An unexpected error occurred.Please try again.");
}
}
input.close();
}
}