/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author msi
 */
abstract public class DataBase {

    ArrayList<Record> records;
    private String filename;

    public DataBase(String filename) {
        this.filename = filename;
        records = new ArrayList<>();
    }

    public Record getRecord(String key) {
        for (Record cp : records) {
            if (cp.GetSearchKey().equals(key)) {
                return cp;
            }
        }
        return null;
    }

    public void readFromFile() throws FileNotFoundException {
        File f = new File(filename);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String Line = s.nextLine();
            records.add(createRecordFrom(Line));
        }
        s.close();
    }

    public abstract Record createRecordFrom(String Line);

    public void insertRecord(Record cp) {
        records.add(cp);
    }

    public boolean contains(String key) {
        for (Record cp : records) {
            if (cp.GetSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).GetSearchKey().equals(key)) {
                records.remove(i);
                break;
            }
        }
    }

    public ArrayList<Record> returnAllRecords() {
        return records;
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

