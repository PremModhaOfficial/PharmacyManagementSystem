import MyDatastructures.java.MyDate;
import MyDatastructures.java.MyHashMap;

import java.util.Scanner;

public class PharmacyManagement {
    MyHashMap<String, Medicine> medicines;
    MyHashMap<MyDate, Medicine> expiryDates;

    public MyDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(MyDate currentDate) {
        this.currentDate = currentDate;
    }

    private MyDate currentDate;
    static Scanner scanner = new Scanner(System.in);

    public PharmacyManagement() {
        medicines = new MyHashMap<>();
        expiryDates = new MyHashMap<>();
    }

    public void addMedicine(String name, int quantity, double price, MyDate expirationDate, String supplier) {
        Medicine m = new Medicine(name, quantity, price, expirationDate, supplier);
        medicines.put(name, m);
        expiryDates.put(expirationDate, m);
    }

    public void remove(String medicineName) {
        if (medicines.get(medicineName) == null) {
            System.out.println("No Such Medicine");
            return;
        }
        MyDate medicineExpiryDate = medicines.get(medicineName).getExpiry();
        expiryDates.remove(medicineExpiryDate);
        medicines.remove(medicineName);
    }

    public void update() {
        String medicineName = scanner.nextLine();
        Medicine m = medicines.get(medicineName);
        if (m == null) {
            System.out.println("No Such Medicine");
            return;
        }


        expiryDates.remove(m.getExpiry());
        while (true) {
            System.out.println("""
                    What do you want to update?
                                        1. name
                                        2. expiry-date
                                        3. supplier
                                        4. price
                                        """);
            switch (scanner.nextLine()) {
                case "1":
                    String newName = scanner.nextLine();
                    m.setName(newName);
                    break;

                case "2":
                    System.out.print("type new Date in 'yyyy-mm-dd' format ");
                    MyDate newDate = new MyDate((scanner.nextLine()));
                    m.setExpiry(newDate);
                    break;

                case "3":
                    System.out.print("Enter New Supplier Name: ");
                    m.setSupplier(scanner.nextLine());
                    break;

                case "4":
                    System.out.print("Enter new Price: ");
                    m.setPrice(scanner.nextDouble());
                    break;

                case "0":
                    return;
            }
        }
    }

    public void removeExpiredMedicine() {
        System.out.println("Medicine that have been expired:\n");
        int expiredMedicieCount = 0;
        for (int i = 0; i < expiryDates.capacity; i++) {
            MyHashMap.Node<MyDate, Medicine> n = expiryDates.getTable()[i];
            while (n != null) {
                if (currentDate.greaterThan(n.getKey())) {
                    System.out.println("The Medicine: " + n.value.getName() + " has been expired and Will be removed now...");
                    medicines.remove(n.value.getName());
                    expiryDates.remove(n.getKey());
                    expiredMedicieCount++;
                }
                n = n.next;
            }
        }
        System.out.println("Total of " + expiredMedicieCount + " products has been expired and has been removed.");
    }
}