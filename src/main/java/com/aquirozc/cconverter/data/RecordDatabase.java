package com.aquirozc.cconverter.data;

import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RecordDatabase {

    private static ArrayList<ExchangeRecord> list;

    @SuppressWarnings("unchecked")
    public static void init(){

        try (FileInputStream fStream = new FileInputStream("./History.aquirozc01"); ObjectInputStream oStream = new ObjectInputStream(fStream)){
            list = (ArrayList<ExchangeRecord>)oStream.readObject();
        }catch (Exception e){
            list = new ArrayList<>();
        }

    }

    public static void saveRecord(ExchangeRecord r){

        list.add(r);

        try (FileOutputStream fStream = new FileOutputStream("./History.aquirozc01"); ObjectOutputStream oStream = new ObjectOutputStream(fStream)){
            oStream.writeObject(list);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static List<ExchangeRecord> getList(){
        return list;
    }
    
}
