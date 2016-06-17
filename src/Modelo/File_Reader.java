/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.logging.Level;

/**
 *
 * @author Silvia Araya J
 * @param <T>
 */
public class File_Reader implements Iterator {

    private BufferedReader bufferReader;
    private String path;
    private boolean haveNext;
    private Lock lock;
    
    public File_Reader(String path) {
        this.path = path;
        haveNext = false;
        lock = new Lock();
        File_Serch();
    }
    
    public String getFileName(){
        return path;
    }

    private void File_Serch() {
        try {
            openFile(path);
            haveNext = true;
        } catch (FileNotFoundException e) {
            java.util.logging.Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void openFile(String path) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(path);
        bufferReader = new BufferedReader(new InputStreamReader(stream));
    }

    private Object readLine() {
        Object info = null;
        try {
            info = bufferReader.readLine();
        } catch (IOException ex) {
            return null;
        }
        return info;
    }

    @Override
    public Object next() {
        Object lector = readLine();  
        if (lector == null) {
            haveNext = false;
            closeBuffer(); 
            lector = null;
        }
        return lector;
    }

    @Override
    public boolean hasNext() {
        return haveNext;
    }

    private void closeBuffer() {
        try {
            bufferReader.close();
        } catch (IOException e) {
            java.util.logging.Logger.getLogger(File_Reader.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
