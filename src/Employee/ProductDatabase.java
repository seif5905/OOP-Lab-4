
package Employee;

import Core.Database;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ProductDatabase extends Database<Product>{
    
    public ProductDatabase(String fileName){
        super(fileName);
        this.records = new ArrayList<>();
    }
    @Override
    public void readFromFile(){
        // opens the file and stores the data in the arraylist of the instance inside file
        try (Scanner scan = new Scanner(new File(this.fileName))){
            while (scan.hasNextLine()){
            String line = scan.nextLine();
            records.add(createRecordFrom(line));
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
    }
    @Override
    public Product createRecordFrom(String line){ 
        String[] words = line.split(",");
        
        String productID = words[0];
        String productName = words[1];
        String manufacturerName = words[2];
        String supplierName = words[3];
        String Squantity = words[4];
        String Sprice = words[5];
        int quantity = Integer.parseInt(Squantity);
        double price = Double.parseDouble(Sprice);
        
        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        
        return product;
    }
}
