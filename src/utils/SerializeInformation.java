package utils;

import input.g_input.CreatePythonScript;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeInformation {
    public static void write(String fname, Object obj) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) {
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Object> read(String fname){
        List<Object> res = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))){
            try {
                res.add(in.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
}
