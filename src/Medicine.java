import main.java.MyDate;

public class Medicine {
    private  String name, supplier;
    private int quantity;

    private double price;
    private MyDate expiry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MyDate getExpiry() {
        return expiry;
    }

    public void setExpiry(MyDate expiry) {
        this.expiry = expiry;
    }

    public Medicine(String name, int quantity, double price, MyDate expiry, String supplier) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiry = expiry;
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "0000000000000000000000000000000" +
                "\nname     = '" + name + '\'' +
                "\nquantity = " + quantity +
                "\nprice    = " + price +
                "\nexpiry   = " + expiry;

    }
}