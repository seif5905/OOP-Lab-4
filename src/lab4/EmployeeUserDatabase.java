/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author msi
 */
public class EmployeeUserDatabase extends DataBase{
 
    
    
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    
  public EmployeeUser createRecordFrom(String Line)
  {
       String[] token=Line.split(",");
          String Id,name,email,address,phonenumber;
          Id=token[0];
          name=token[1];
          email=token[2];
          address=token[3];
          phonenumber=token[4];
          EmployeeUser em=new EmployeeUser(Id, name, email, address, phonenumber);
          return em;
      
  }}

