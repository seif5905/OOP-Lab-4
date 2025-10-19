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
public class EmployeeUserDatabase {
  private ArrayList<EmployeeUser> records = new ArrayList<>();
  private String filename;
    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
    }

  public void readFromFile()throws FileNotFoundException
  {
      File f=new File(filename);
      Scanner s=new Scanner(f);
      while(s.hasNextLine())
      {
          String Line =s.nextLine();
         EmployeeUser em=creatRecordFrom(Line);
         records.add(em);
      }
      s.close();
  }
  public EmployeeUser creatRecordFrom(String Line)
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
      
  }
  public ArrayList<EmployeeUser> returnAllRecords()
  {
      return records;
  }
  public boolean contains(String key)
  {
      for(int i=0;i<records.size();i++)
      {
          if(records.get(i).GetSearchKey().equals(key))
              return true;
      }
      return  false;
  }
  public EmployeeUser getRecord(String key)
  {
      for(int i=0;i<records.size();i++)
      {
          EmployeeUser em=records.get(i);
          if(records.contains(key))
              return em;
      }
      return null;
  }
  public void insertRecord(EmployeeUser record) 
  {
      records.add(record);
  }
  public void deleteRecord(String key)
  {
      for(int i=0;i<records.size();i++)
      {
          EmployeeUser em=records.get(i);
          if(records.contains(key))
              records.remove(i);
      }
  }
  public void saveToFile() throws FileNotFoundException
  {
      
          PrintWriter w=new PrintWriter(filename);
          for(int i=0;i<records.size();i++){
              w.println(records.get(i).lineRepresentation());
              if(i<records.size()-1)
                  w.write("\n");
          }
          w.close();
      } 
  }
  

