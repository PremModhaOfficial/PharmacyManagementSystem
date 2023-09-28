import java.util.*;
class Medicine {
    String name;
    int quantity;
    double price;

    public Medicine(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class Node {
    Medicine medicine;
    Node next;

    public Node(Medicine medicine) {
        this.medicine = medicine;
        this.next = null;
    }
}

class Pharmacy {
    Node head;

    public void addMedicine(String name, int quantity, double price) {
        Medicine med = new Medicine(name, quantity, price);
        Node newNode = new Node(med);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void removeMedicine(String name) {
        if (head == null) {
            return;
        }

        if (head.medicine.name.equals(name)) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null && !temp.next.medicine.name.equals(name)) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public void updateMedicine(String name, int quantity, double price) {
        Node temp = head;
        while (temp != null && !temp.medicine.name.equals(name)) {
            temp = temp.next;
        }

        if (temp != null) {
            temp.medicine.quantity = quantity;
            temp.medicine.price = price;
        }
    }

    public Medicine searchMedicine(String name) {
        Node temp = head;
        while (temp != null) {
            if (temp.medicine.name.equals(name)) {
                return temp.medicine;
            }
            temp = temp.next;
        }
        return null;
    }

    public void displayInventory() {
        Node temp = head;
        while (temp != null) {
            Medicine med = temp.medicine;
            System.out.println("Name: " + med.name + ", Quantity: " + med.quantity + ", Price: " + med.price);
            temp = temp.next;
        }
    }
}
class Main {
    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Medicine");
            System.out.println("2. Remove Medicine");
            System.out.println("3. Update Medicine");
            System.out.println("4. Search Medicine");
            System.out.println("5. Display Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter medicine name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    pharmacy.addMedicine(name, quantity, price);
                    break;

                case 2:
                    System.out.print("Enter medicine name to remove: ");
                    String removeName = scanner.nextLine();
                    pharmacy.removeMedicine(removeName);
                    break;

                case 3:
                    System.out.print("Enter medicine name to update: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    pharmacy.updateMedicine(updateName, newQuantity, newPrice);
                    break;

                case 4:
                    System.out.print("Enter medicine name to search: ");
                    String searchName = scanner.nextLine();
                    Medicine foundMedicine = pharmacy.searchMedicine(searchName);
                    if (foundMedicine != null) {
                        System.out.println("Found: " + foundMedicine.name + ", Quantity: " + foundMedicine.quantity + ", Price: " + foundMedicine.price);
                    } else {
                        System.out.println("Medicine not found.");
                    }
                    break;

                case 5:
                    pharmacy.displayInventory();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}