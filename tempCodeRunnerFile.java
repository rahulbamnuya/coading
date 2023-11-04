import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSystem {
    private static Map<Integer, Integer> accountDatabase = new HashMap<>();
    private static Map<Integer, Double> accountBalances = new HashMap<>();

    public static void main(String[] args) {
        // Initialize the ATM database with account numbers, PINs, and balances
        accountDatabase.put(12345, 6789); // Replace with actual account numbers and PINs
        accountBalances.put(12345, 1000.0); // Replace with actual account balances

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM System");
        int attempts = 3; // Number of login attempts allowed

        while (attempts > 0) {
            System.out.print("Enter your ATM number: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter your PIN: ");
            int enteredPIN = scanner.nextInt();

            if (validateAccount(accountNumber, enteredPIN)) {
                System.out.println("Login successful. You can now perform transactions.");
                int choice;
                do {
                    System.out.println("1. Change PIN");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Check Balance");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            changePIN(accountNumber);
                            break;
                        case 2:
                            deposit(accountNumber);
                            break;
                        case 3:
                            withdraw(accountNumber);
                            break;
                        case 4:
                            checkBalance(accountNumber);
                            break;
                        case 5:
                            System.out.println("Thank you for using the ATM. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice != 5);

                break;
            } else {
                attempts--;
                System.out.println("Invalid ATM number or PIN. " + attempts + " attempts remaining.");
            }
        }

        if (attempts == 0) {
            System.out.println("Maximum login attempts reached. Your account is locked.");
        }
    }

    private static boolean validateAccount(int accountNumber, int enteredPIN) {
        Integer storedPIN = accountDatabase.get(accountNumber);
        return storedPIN != null && storedPIN == enteredPIN;
    }

    private static void changePIN(int accountNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your new PIN: ");
        int newPIN = scanner.nextInt();
        accountDatabase.put(accountNumber, newPIN);
        System.out.println("PIN changed successfully.");
    }

    private static void deposit(int accountNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        double currentBalance = accountBalances.get(accountNumber);
        accountBalances.put(accountNumber, currentBalance + depositAmount);
        System.out.println("Deposit successful.");
    }

    private static void withdraw(int accountNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        double currentBalance = accountBalances.get(accountNumber);

        if (withdrawAmount <= currentBalance) {
            accountBalances.put(accountNumber, currentBalance - withdrawAmount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void checkBalance(int accountNumber) {
        double currentBalance = accountBalances.get(accountNumber);
        System.out.println("Your current balance is: $" + currentBalance);
    }
}