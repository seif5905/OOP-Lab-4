
package Core;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public abstract class Database<type extends DatabaseInterface> {
    protected String fileName;
    protected ArrayList<type> records;
    
    public Database(String fileName){
        this.fileName = fileName;
        this.records = new ArrayList<>();
    }
    public abstract void readFromFile();
    public abstract type createRecordFrom(String line);
    
    public void saveToFile(){
        try (FileWriter writer = new FileWriter(this.fileName, false)){
            for (int i = 0; i < this.records.size(); i++){
                writer.write(records.get(i).lineRepresentation()+"\n");
            }
            System.out.println("File overwritten successfully!");
        }
        catch (IOException e){
            System.out.println("Error writing to file!");
            e.printStackTrace();
        }
    }
    public boolean contains(String key){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getSearchKey().equals(key)){
                return true;
            }
        }
        return false;
    }
    public void insertRecord(type record){
        if(!contains(record.getSearchKey()))
            records.add(record);
    }
    public void deleteRecord(String key){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getSearchKey().equals(key)){
                records.remove(i);
                return;
            }
        }
    }
    public type getRecord(String key){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getSearchKey().equals(key)){
                return records.get(i);
            }   
        }
        return null;
    }
    public ArrayList<type> returnAllRecords(){
        return new ArrayList<>(this.records);
    }
}
