import MyDatastructures.java.MyDate;
import java.util.Scanner;

public class Main {
    static PharmacyManagement pharmacy;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        pharmacy = new PharmacyManagement();
        System.out.println("Enter Current Date in 'yyyy-mm-dd' format");
        pharmacy.setCurrentDate(new MyDate(scanner.nextLine()));

        addDummyMeds();
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Medicine");
            System.out.println("2. Remove Medicine");
            System.out.println("3. Update Medicine");
            System.out.println("4. Search Medicine");
            System.out.println("5. Display Inventory");
            System.out.println("6. Remove Expired Medicine");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addMedicine();
                    break;

                case 2:
                    removeMedicine();
                    break;

                case 3:
                    updateMedicine();
                    break;

                case 4:
                    searchMedicine();
                    break;

                case 5:
                    displayInventory();
                    break;

                case 6:
                    pharmacy.removeExpiredMedicine();

                case 7:
                    System.out.println("exiting... \nThank you ;)");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayInventory() {
        pharmacy.medicines.display();
    }

    private static void searchMedicine() {
        System.out.println("Enter Medicine Name");
        Medicine medicine = pharmacy.medicines.get(scanner.nextLine());
        if (medicine == null) {
            System.out.println("No such Medicine");
        }else {
            System.out.println(medicine);
        }
    }

    private static void updateMedicine() {
        System.out.print("Enter Medicine Name:");
        pharmacy.update();
    }

    private static void removeMedicine() {
        System.out.print("Enter Medicine Name:");
        pharmacy.remove(scanner.nextLine());
    }

    private static void addMedicine() {
        System.out.print("Enter medicine name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Expiry-date in 'YYYY-MM-DD' format");
        String dateString = scanner.nextLine();
        MyDate expiryDate = new MyDate(dateString);
        System.out.print("Enter Supplier name :");
        String supplier = scanner.nextLine();
        pharmacy.addMedicine(name, quantity, price, expiryDate, supplier);

    }

    private static void addDummyMeds() {
        /**
         * Adds dummy items
         * In both the HashMaps
         */
        for (int i = 1; i <= 55; i++) {
            String name = "Medicine" + i;
            int quantity = 100 + i; // Adjust quantity as needed
            double price = 5.0 + (i * 0.5); // Adjust price as needed
            MyDate expirationDate = new MyDate(MyDate.randomDate()); // Set an arbitrary expiration date
            String supplier = "Supplier" + i;
            pharmacy.addMedicine(name, quantity, price, expirationDate, supplier);
        }
    }
}