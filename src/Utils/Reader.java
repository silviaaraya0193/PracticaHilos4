/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import flexjson.JSONDeserializer;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author viccr
 */
public class Reader<T> {
    //************************leer xml******************************
    public T readXML(String filepath){
        XMLDecoder d;
        T obj = null;
        try {
            d = new XMLDecoder(new BufferedInputStream(new FileInputStream(filepath)));
            obj = (T) d.readObject();
            d.close();
                    } catch (FileNotFoundException ex) {
            System.out.println("error en el leer xml");
        }
        return obj;
    }
    //*************************leer txt**********************************
//      File archivo = null;
//      FileReader fr = null;
//      BufferedReader br = null;
//      String name = null;
//      public void leerArchivoTXT(){
//      try {
//         // Apertura del fichero y creacion de BufferedReader para poder
//         // hacer una lectura comoda (disponer del metodo readLine()).
//         archivo = new File (name);
//         fr = new FileReader (archivo);
//         br = new BufferedReader(fr);
//
//         // Lectura del fichero
//         String linea;
//         while((linea=br.readLine())!=null)
//            System.out.println(linea);
//      }
//      catch(Exception e){
//         e.printStackTrace();
//      }finally{
//         // En el finally cerramos el fichero, para asegurarnos
//         // que se cierra tanto si todo va bien como si salta 
//         // una excepcion.
//         try{                    
//            if( null != fr ){   
//               fr.close();     
//            }                  
//         }catch (Exception e2){ 
//            e2.printStackTrace();
//         }
//      }
//      }
//    //********************************************************************
//    private BufferedReader lector;
//    
//    public boolean abrirArchivo(String filepath){
//        try {
//            lector = new BufferedReader(new FileReader(filepath));
//            return true;
//        } catch (FileNotFoundException ex) {
//            System.out.println("Error en abrir archivo del lector");
//        }
//        return false;
//    }
//    
//    public Object leerArchivo(Object estudiante){
//        Object est = null;
//        try {
//            String linea = lector.readLine();
//            String datos[];
//            int n;
//            while(linea != null){
//                est = new Object();
//                n = 0;
//                datos = linea.split(" ");
//                linea = lector.readLine();
//            }
//        } catch (IOException ex) {
//            System.out.println("Error al leer archivo");
//        }
//        return est;
//    }
//    public void cerrarLector(){
//        if(lector != null){
//            try {
//                lector.close();
//            } catch (IOException ex) {
//                System.out.println("Error al cerrar Lector");
//            }
//        }
//    }
    public void leeContenidoTXT(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
        }
        b.close();
    }
    //**************************leer json***********************************
     private BufferedReader brR;
    
    private String leerConThrow(String filepath) throws Exception{
        brR = new BufferedReader(new FileReader(filepath));
        StringBuilder sb = new StringBuilder();
        String line = brR.readLine();
        while(line != null){
            sb.append(line);
            sb.append(System.lineSeparator());
            line = brR.readLine();
        }
        return sb.toString();
    }
    public String leeFile(String filepath){
        String dev = null;
        try {
            dev = leerConThrow(filepath);
        } catch (Exception ex) {
            System.out.println("error en el lee file con dev");
        } finally{
            if(brR != null){
                try {
                    brR.close();
                } catch (IOException ex) {
                    System.out.println("Error en leefile, de la clase reader");
                }
            }
        }
        return dev;
    }
    
    public T leerJson(String filepath){
        T obj = null;
        String text = this.leeFile(filepath);
        if(text != null){
            obj = new JSONDeserializer<T>().deserialize(text);
        }
        return obj;
    }
    
}
