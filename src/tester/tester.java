/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tester;

import java.util.Scanner;
import java.util.Random;
import Admin.*;
import Employee.*;
import java.time.LocalDate;



public class tester {
    public static void main(String[] args){
        AdminRole admin = new AdminRole();
        EmployeeRole employee = new EmployeeRole();
        while(true){
        System.out.println("Welcome To The Inventory Management System!");
        System.out.println("Select User:");
        System.out.println("[1] Admin");
        System.out.println("[2] Employee");
        System.out.println("[3] Exit");
        Scanner scan = new Scanner(System.in);
        int user = scan.nextInt();
        scan.nextLine();
        
        if(user == 1){
            while(true){
                System.out.println("Hello Admin! :) ");
                System.out.println("Select From The Following:");
                System.out.println("[1] Add Employee");
                System.out.println("[2] Get List Of Employees");
                System.out.println("[3] Remove Employee");
                System.out.println("[4] Logout");
                int adminChoice = scan.nextInt();
                scan.nextLine();
                if(adminChoice == 1){
                    System.out.print("Enter Name Of Employee: ");
                    String name = scan.nextLine();
                    System.out.print("Enter Email Of Employee: ");
                    String email = scan.nextLine();
                    System.out.print("Enter Address Of Employee: ");
                    String address = scan.nextLine();
                    System.out.print("Enter Phone Number Of Employee: ");
                    String phoneNumber = scan.nextLine();

                    Random rand = new Random();
                    int IemployeeId = rand.nextInt(9000) + 1000;
                    String employeeId = "E"+Integer.toString(IemployeeId);

                    admin.addEmployee(employeeId, name, email, address, phoneNumber);
                }
                else if(adminChoice == 2){
                    EmployeeUser employeesList[] = admin.getListOfEmployees();
                    for(EmployeeUser Employee : employeesList){
                        System.out.print(Employee.lineRepresentation()+"\n");
                    }
                }
                else if(adminChoice == 3){
                    System.out.print("Enter Employee ID: ");
                    String id = scan.nextLine();
                    admin.removeEmployee(id);
                }
                else if(adminChoice == 4){
                    admin.logout();
                    break;
                }

            }
        }
        
        else if(user == 2){
            while(true){
                System.out.println("Hello Employee! :) ");
                System.out.println("Select From The Following:");
                System.out.println("[1] Add Product");
                System.out.println("[2] Get List Of Products");
                System.out.println("[3] Get List Of Purchasing Operations");
                System.out.println("[4] Purchase Product");
                System.out.println("[5] Return Product");
                System.out.println("[6] Apply Payment");
                System.out.println("[7] Logout");
                int employeeChoice = scan.nextInt();
                scan.nextLine();

                if(employeeChoice == 1){
                    System.out.print("Enter Name Of Product: ");
                    String productName = scan.nextLine();
                    System.out.print("Enter Name Of Manufacturer: ");
                    String manufacturerName = scan.nextLine();
                    System.out.print("Enter Name Of Supplier: ");
                    String supplierName = scan.nextLine();
                    System.out.print("Enter Quantity Of Product: ");
                    int quantity = scan.nextInt();
                    System.out.print("Enter Price Of Product: ");
                    double price = scan.nextDouble();

                    Random rand = new Random();
                    int IproductID = rand.nextInt(9000) + 1000;
                    String productID = "P"+Integer.toString(IproductID);

                    employee.addProduct(productID, productName, manufacturerName, supplierName, quantity, price);

                }
                else if(employeeChoice == 2){
                    Product[] productsList = employee.getListOfProducts(); 
                    for(Product product : productsList){
                        System.out.println(product.lineRepresentation());
                    }
                }
                else if(employeeChoice == 3){
                    CustomerProduct[] purchasingsList = employee.getListOfPurchasingOperations();
                    for(CustomerProduct purchasing : purchasingsList){
                        System.out.println(purchasing.lineRepresentation());
                    }
                }
                else if(employeeChoice == 4){
                    //enter customer SSN,product ID, Today's date
                    System.out.print("Enter Customer SSN: ");
                    String customerSSN = scan.nextLine();
                    System.out.print("Enter Product ID: ");
                    String productID = scan.nextLine();
                    System.out.print("Enter Today's Date (separated by DD-MM-YYYY): ");
                    String date = scan.nextLine();
                    String[] words = date.split("-");
                    int day = Integer.parseInt(words[0]);
                    int month = Integer.parseInt(words[1]);
                    int year = Integer.parseInt(words[2]);
            
                    LocalDate purchaseDate = LocalDate.of(year, month, day);
                    employee.purchaseProduct(customerSSN, productID, purchaseDate);
                }
                else if(employeeChoice == 5){
                    //enter customer SSN,product ID, purchase date , Today's date
                    System.out.print("Enter Customer SSN: ");
                    String customerSSN = scan.nextLine();
                    System.out.print("Enter Product ID: ");
                    String productID = scan.nextLine();
                    System.out.print("Enter Purchase Date (separated by DD-MM-YYYY): ");
                    String date = scan.nextLine();
                    String[] words = date.split("-");
                    int day = Integer.parseInt(words[0]);
                    int month = Integer.parseInt(words[1]);
                    int year = Integer.parseInt(words[2]);
            
                    LocalDate purchaseDate = LocalDate.of(year, month, day);
                    
                    System.out.print("Enter Today's Date (separated by DD-MM-YYYY): ");
                    String Date = scan.nextLine();
                    String[] Words = Date.split("-");
                    int Day = Integer.parseInt(Words[0]);
                    int Month = Integer.parseInt(Words[1]);
                    int Year = Integer.parseInt(Words[2]);
            
                    LocalDate returnDate = LocalDate.of(Year, Month, Day);
                    
                    employee.returnProduct(customerSSN, productID, purchaseDate, returnDate);
                }
                else if(employeeChoice == 6){
                    //enter customer SSN and today's date
                    System.out.print("Enter Customer SSN: ");
                    String customerSSN = scan.nextLine();
                    System.out.print("Enter Purchase Date (separated by DD-MM-YYYY): ");
                    String date = scan.nextLine();
                    String[] words = date.split("-");
                    int day = Integer.parseInt(words[0]);
                    int month = Integer.parseInt(words[1]);
                    int year = Integer.parseInt(words[2]);
            
                    LocalDate purchaseDate = LocalDate.of(year, month, day);
                    
                    employee.applyPayment(customerSSN, purchaseDate);
                }
                else if(employeeChoice == 7){
                    employee.logout();
                    break;
                }
            }
        }
        else if(user == 3){
            break;
        }
        }
    }
}
