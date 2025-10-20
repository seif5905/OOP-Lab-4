package lab4;

public class Product implements Record{
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private double price;

    public Product(String productID, String productName, String manufacturerName,
            String supplierName, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0)
            System.out.println("Quantity Cannot Be A Negative Number");
        else
            this.quantity = quantity;
    }

    public String lineRepresentation() {
        return (this.productID + "," + this.productName + "," + this.manufacturerName
                + "," + this.supplierName + "," + this.quantity + "," + this.price);
    }

    public String GetSearchKey() {
        return this.productID;
    }
}
