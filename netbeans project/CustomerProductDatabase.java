package lab4;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Zaid
 */
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CustomerProductDatabase extends DataBase {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }


    public Record createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length == 4) {
            String ssn = data[0].trim();
            String productId = data[1].trim();
            String date = data[2].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate d = LocalDate.parse(date, formatter);
            return new CustomerProduct(ssn, productId, d);
        }
        return null;
    }

}
