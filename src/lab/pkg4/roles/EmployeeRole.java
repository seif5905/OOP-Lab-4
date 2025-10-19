/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg4.roles;

import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author RinadMostafa
 */
public class EmployeeRole {

    private productDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        this.productsDatabase = new productDatabase("Products.txt");
        this.customerProductDatabase = new CustomerProductDatabase("CustomerProduct.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public ProductDatabase getProductsdatabase() {
        return productsdatabase;
    }

    public void setProductsdatabase(ProductDatabase productsdatabase) {
        this.productsDtabase = productsdatabase;
    }

    public CustomerProductDatabase getCustomerProductDatabase() {
        return customerProductDatabase;
    }

    public void setCustomerProductDatabase(CustomerProductDatabase customerProductDatabase) {
        this.customerProductDatabase = customerProductDatabase;
    }

    public void addProduct(String producID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        if (productsDatabase.contains(producID)) {
            System.out.println("Id already exists");
            return;
        } else {
            product p = new product(producID, productName, manufacturerName, supplierName, quantity, price);
            productsDatabase.insertRecord(p);
            productsDatabase.saveToFile();
            System.out.println("productName " + productName + " added successfully");
        }
    }

    public product[] getListOfproducts() {
        productsDatabase.readFromFile();
        ArrayList<product> list = productsDatabase.returnAllRecords();
        product[] products = new product[list.size()];
        for (int i = 0; i < list.size(); i++) {
            products[i] = list.get(i);
        }
        return products;
    }

    public customerProduct[] getListOfpurchasingOperations() {
        customerProductDatabase.readFromFile();
        customerProductDatabase.returnAllRecords();
        ArrayList<CustomrProduct> list = customerProductDatabase.returnAllRecords();
        customerProduct[] customerProducts = new customerProduct[list.size()];
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
            product p = productsDatabase.getRecord(productID);
            if (p.getQuantity() == 0) {
                System.out.println("product out of stock");
                return false;
            } else {
                p.setQuantity(p.getQuantity() - 1);

                customerProduct cp = new customerProduct(customerSSN, productID, purchaseDate, false);
                customerProductDatabase.insertRecord(cp);
                productsDatabase.saveToFile();
                customerProductDatabase.saveToFile();
                return true;
            }

        }
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) {
            System.out.println("return date is befor purshase date");
            return -1;
        }
        if (!customerProductDatabase.contains(productID)) {
            System.out.println("purchase date not found");
            return -1;
        }
        long betweenPurchaseReturn = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (betweenPurchaseReturn > 14) {
            System.out.println("more than 14 days");
            return -1;
        }
        product p = productsDatabase.getRecord(productID);
        p.setQuantity(p.getQuantity() + 1);
        customerProductDatabase.deleteRecord(customerSSN, productID, purchaseDate);
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        return p.getPrice();
    }
    
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate)
    {
    list<customerProduct>records=customerProductDatabase.returnAllRecords();
    for (int i=0;i<records.size();i++){
        customerProduct cp = records.get(i);
        if(cp.getCustomerId().equals(customerSSN)&&cp.getPurchaseDate().equals(purchaseDate)){
            if(cp.isPaid()){
                System.out.println("purchase paid");
                return false;
            }
            cp.setPaid(true);
            customerProductDatabase.saveToFile();
            return true;
        }
    }
    return false;
    }
            
}
