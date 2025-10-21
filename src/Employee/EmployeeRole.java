
package Employee;

import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        this.productsDatabase = new ProductDatabase("Products.txt");
        this.customerProductDatabase = new CustomerProductDatabase("CustomerProduct.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public ProductDatabase getProductsdatabase() {
        return productsDatabase;
    }

    public void setProductsdatabase(ProductDatabase productsdatabase) {
        this.productsDatabase = productsdatabase;
    }

    public CustomerProductDatabase getCustomerProductDatabase() {
        return customerProductDatabase;
    }

    public void setCustomerProductDatabase(CustomerProductDatabase customerProductDatabase) {
        this.customerProductDatabase = customerProductDatabase;
    }

    public void addProduct(String producID, String productName, String manufacturerName, String supplierName, int quantity, double price) {
        if (productsDatabase.contains(producID)) {
            System.out.println("Id already exists");
            return;
        } else {
            Product p = new Product(producID, productName, manufacturerName, supplierName, quantity, price);
            productsDatabase.insertRecord(p);
            //productsDatabase.saveToFile();
            System.out.println("productName " + productName + " added successfully");
        }
    }

    public Product[] getListOfProducts() {
        //productsDatabase.readFromFile();
        ArrayList<Product> list = productsDatabase.returnAllRecords();
        Product[] products = new Product[list.size()];
        for (int i = 0; i < list.size(); i++) {
            products[i] = list.get(i);
        }
        return products;
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        //customerProductDatabase.readFromFile();
        //customerProductDatabase.returnAllRecords();
        ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();
        CustomerProduct[] customerProducts = new CustomerProduct[list.size()];
        for (int i = 0; i < list.size(); i++) {
            customerProducts[i] = list.get(i);
        }
        return customerProducts;
    }

    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (!productsDatabase.contains(productID)) {
            System.out.print("product not found");
            return false;
        } else {
            Product p = productsDatabase.getRecord(productID);
            if (p.getQuantity() == 0) {
                System.out.println("product out of stock");
                return false;
            } else {
                p.setQuantity(p.getQuantity() - 1);

                CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
                customerProductDatabase.insertRecord(cp);
                //productsDatabase.saveToFile();
                //customerProductDatabase.saveToFile();
                return true;
            }

        }
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) {
            System.out.println("return date is before purshase date");
            return -1;
        }
        if (!customerProductDatabase.contains(customerSSN+","+productID+","+purchaseDate.getDayOfMonth()
                    +"-"+purchaseDate.getMonthValue()+"-"+purchaseDate.getYear())) {
            System.out.println("Details Not Found");
            return -1;
        }
        long betweenPurchaseReturn = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (betweenPurchaseReturn > 14) {
            System.out.println("more than 14 days");
            return -1;
        }
        Product p = productsDatabase.getRecord(productID);
        p.setQuantity(p.getQuantity() + 1);
        customerProductDatabase.deleteRecord(customerSSN+","+ productID+","+ purchaseDate.getDayOfMonth()+"-"+
                purchaseDate.getMonthValue()+"-"+purchaseDate.getYear());
        //productsDatabase.saveToFile();
        //customerProductDatabase.saveToFile();
        return p.getPrice();
    }
    
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate)
    {
    List<CustomerProduct>records=customerProductDatabase.returnAllRecords();
    for (int i=0;i<records.size();i++){
        CustomerProduct cp = records.get(i);
        if(cp.getCustomerSSN().equals(customerSSN)&&cp.getPurchaseDate().equals(purchaseDate)){
            if(cp.isPaid()){
                System.out.println("purchase paid");
                return false;
            }
            cp.setPaid(true);
            //customerProductDatabase.saveToFile();
            return true;
        }
    }
    return false;
    }
            
}