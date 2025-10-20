/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author Zaid
 */
import java.util.ArrayList;

public class ProductDatabase extends DataBase{

    public ProductDatabase(String filename) {
        super(filename);
    }

   
    
    @Override
    public Record createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length == 6) {
            String id = data[0].trim();
            String name = data[1].trim();
            String manufacturer = data[2].trim();
            String supplier = data[3].trim();
            int quantity = Integer.parseInt(data[4].trim());
            float price = Float.parseFloat(data[5].trim());
            return new Product(id, name, manufacturer, supplier, quantity, price);
        }
        return null;
    }

    
}