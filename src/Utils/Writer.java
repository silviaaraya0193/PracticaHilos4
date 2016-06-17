/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author viccr
 */
public class Writer {

    //******************************escribir json******************************
    private FileWriter escritor;

    private void escribeConThrow(String filepath, String text) throws Exception {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        // text="Prueba de escritura en archivos txt miercoles frio 15/06/2016";
        //System.out.println("El texto que tiene para escribir es: "+text);
        escritor = new FileWriter(file);
        escritor.write(text);
        escritor.flush();
    }

    public void escribeJSON(String filepath, String text) {
        try {
            escribeConThrow(filepath, text);
            escritor.close();
        } catch (Exception ex) {
            System.out.println("Error en es escribir json");
        }
    }

    //*******************************escribir xml******************************
    public void escribeXML(String filepath, Object obj) {
        XMLEncoder e;
        try {

            e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filepath)));
            e.writeObject(obj);
            e.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error en el escribe xml");;
        }
    }
    //****************************escribir txt*********************************
//    private BufferedWriter escritorTXT;
//
//   
//    public boolean abrirArchivo(String filepath){
//        try {
//            escritorTXT = new BufferedWriter(new FileWriter(filepath));
//            return true;
//        } catch (IOException ex) {
//            System.out.println("Error al crear, abrir el archivo");
//        }
//        return false;
//    }
//
//    /**
//     *
//     * @param <E>
//     * @param estudiante
//     */
//    public <E> void Escritor(Object estudiante){
//        try {
//            escritor.write(estudiante.toString());
//        } catch (IOException ex) {
//            System.out.println("Error al escribir el archivo de txt");
//        }
//    }
//    public void cerrarArchivo(){
//        if(escritor != null){
//            try {
//                escritor.close();
//            } catch (IOException ex) {
//                System.out.println("Error al cerrar el archvio de txt");
//            }
//        }
//    }
//    //***********************************************************************
//    FileWriter fichero = null;
//        PrintWriter pw = null;
//        String name = null;
//        public void escribirTXT(){
//        try
//        {
//            fichero = new FileWriter(name);
//            pw = new PrintWriter(fichero);
//
//            for (int i = 0; i < 10; i++)
//                pw.println("Linea " + i);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//           try {
//           // Nuevamente aprovechamos el finally para 
//           // asegurarnos que se cierra el fichero.
//           if (null != fichero)
//              fichero.close();
//           } catch (Exception e2) {
//              e2.printStackTrace();
//           }
//        }
//        }
    //******************************************************************

//    public void escribirTXT(String name, String texto) throws IOException {
//        String sFichero = null;
//        File fichero = new File(sFichero);
//        BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
//        if (fichero.exists()) {
//            bw.write(texto);
//        }else{
//            bw.write(texto);
//            bw.close();
//        }
//    }
    public void escribir(String nombreArchivo,String texto) {
        File f;
        f = new File(nombreArchivo);
//Escritura
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write(texto);//escribimos en el archivo
            wr.append(texto); //concatenamos en el archivo sin borrar lo existente
            //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
            //de no hacerlo no se escribirá nada en el archivo
            wr.close();
            bw.close();
        } catch (IOException e) {
        };
    }
}
