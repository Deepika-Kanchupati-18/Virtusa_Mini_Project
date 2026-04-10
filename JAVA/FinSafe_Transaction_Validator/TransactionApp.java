import java.util.Scanner;

public class TransactionApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Account acc = new Account("Deepu", 1500);

        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Spend");
            System.out.println("3. Mini Statement");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            try {
                handleUserChoice(choice, sc, acc);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            } catch (InSufficientFundsException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 4);

        System.out.println("Session Ended.");
    }

    private static void handleUserChoice(int choice, Scanner sc, Account acc)
            throws InSufficientFundsException {

        switch (choice) {

            case 1:
                System.out.print("Enter amount to deposit: ");
                acc.deposit(sc.nextDouble());
                break;

            case 2:
                System.out.print("Enter amount to spend: ");
                acc.processTransaction(sc.nextDouble());
                break;

            case 3:
                acc.printMiniStatement();
                break;

            case 4:
                break;

            default:
                System.out.println("Invalid selection.");
        }
    }
}
