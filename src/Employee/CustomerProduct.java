package Employee;

import java.time.LocalDate;

public class CustomerProduct {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;
    
    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate){
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }
    public String getCustomerSSN(){
        return this.customerSSN;
    }
    public String getProductID(){
        return this.productID;
    }
    public LocalDate getPurchaseDate(){
        return this.purchaseDate;
    }
    
    public String lineRepresentation(){
        return (this.customerSSN+","+this.productID+","+this.purchaseDate.getDayOfMonth()+
                "-"+this.purchaseDate.getMonthValue()+"-"+this.purchaseDate.getYear()+","+this.paid);
    }
    public boolean isPaid(){
        return this.paid;
    }
    public void setPaid(boolean paid){
        this.paid = paid;
    }
    
    public String getSearchKey(){
        return (this.customerSSN+","+this.productID+","+this.purchaseDate.getDayOfMonth()+
                "-"+this.purchaseDate.getMonthValue()+"-"+this.purchaseDate.getYear());
    }
}
