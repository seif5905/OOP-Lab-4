
package Admin;

import java.util.ArrayList;


public class AdminRole {
    
     private EmployeeUserDatabase database;

    public EmployeeUserDatabase getDatabase() {
        return database;
    }

    public void setDatabase(EmployeeUserDatabase database) {
        this.database = database;
    }
    public AdminRole(){
        this.database=new EmployeeUserDatabase("Employees.txt");
                database.readFromFile();
    }
    
    public void addEmployee(String employeeId, String name,String email,String address,String phoneNumber){
       if(database.contains(employeeId)){
           System.out.println("Id already exists");
           return;
       }
       else
       {
        EmployeeUser e = new EmployeeUser(employeeId,name,email,address,phoneNumber);  
          database.insertRecord(e);
          System.out.println("employee "+name+" added successfully");
       }   
}
    
    public EmployeeUser[] getListOfEmployees(){
        ArrayList<EmployeeUser> list=database.returnAllRecords();
        EmployeeUser[] employees = new EmployeeUser[list.size()];
        for(int i=0;i<list.size();i++)
        {
            employees[i]=list.get(i);
        }
        return employees;
    }
    
    public void removeEmployee(String key){
       if(!database.contains(key)){
          System.out.println("key not found");
          return;
    }
       database.deleteRecord(key);
}
    
    public void logout(){
        database.saveToFile();
    }
    
}
