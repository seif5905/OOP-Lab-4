
package Employee;


import Core.Database;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


public class CustomerProductDatabase extends Database<CustomerProduct>{
    
    
    public CustomerProductDatabase(String fileName){
        super(fileName);
        this.records = new ArrayList<>();
    }
    @Override
    public void readFromFile(){
        // opens the file and stores the data in the arraylist of the instance inside file
        try (Scanner scan = new Scanner(new File(this.fileName))){
            int i = 0;
            while (scan.hasNextLine()){
            String line = scan.nextLine();
            String[] words = line.split(",");
            
            String customerSSN = words[0];
            String productID = words[1];
            
            String date[] = words[2].split("-");
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            
            LocalDate purchaseDate = LocalDate.of(year, month, day);
            
            boolean paid = Boolean.parseBoolean(words[3]);

            records.add(new CustomerProduct(customerSSN, productID, purchaseDate));
            records.get(i).setPaid(paid);
            i++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
    }
    @Override
    public CustomerProduct createRecordFrom(String line){ 
        String[] words = line.split(",");
        
        String customerSSN = words[0];
        String productID = words[1];
        
        String date[] = words[2].split("-");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
            
        LocalDate purchaseDate = LocalDate.of(year, month, day);
        
        CustomerProduct customerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
        
        return customerProduct;
    }
}
