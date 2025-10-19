/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author msi
 */
public class EmployeeUser implements Record {
   private String employeeId;
   private String Name;
   private String Email;
   private String Address;
   private String PhoneNumber;

    public EmployeeUser(String employeeId, String Name, String Email, String Address, String PhoneNumber) {
        this.employeeId = employeeId;
        this.Name = Name;
        this.Email = Email;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }
    @Override
   public String lineRepresentation()
   {
      String Line=employeeId +","+ Name +","+ Email +","+ Address +","+ PhoneNumber;
      return Line;
   }
   @Override
   public String GetSearchKey()
   {  
       return employeeId;
   }
}
