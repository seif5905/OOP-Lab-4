
package Admin;

import Core.Database;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class EmployeeUserDatabase extends Database<EmployeeUser>{
    
    public EmployeeUserDatabase(String fileName){
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
    public EmployeeUser createRecordFrom(String line){ 
        String[] words = line.split(",");
        
        String employeeId = words[0];
        String name = words[1];
        String email = words[2];
        String address = words[3];
        String phoneNumber = words[4];
        
        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        return employee;
    }   
}
