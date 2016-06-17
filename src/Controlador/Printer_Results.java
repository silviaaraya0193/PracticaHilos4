/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Canton;
import Modelo.File_Reader;
import Modelo.Lock;
import Modelo.Provincia;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Silvia Araya J
 */
public class Printer_Results implements Runnable {

    private File_Reader reader;
    private List<Thread> threadList;
    private String name;
    private Thread hilo;
    
    private ManejoEstadistica managerEstadistic;
    private Lock lock;

    public Printer_Results(File_Reader reader, String name, Lock lock) throws IOException {
        this.reader = reader;
        this.name = name;
        threadList = new ArrayList<Thread>();
        this.lock = lock;
        managerEstadistic = new ManejoEstadistica();
    }
    
    public Printer_Results(File_Reader reader, String name,
            Lock lock,HashMap<String,Provincia> provinciasList,HashMap<String,Canton> cantonList ) throws IOException{
         this.reader = reader;
        this.name = name;
        threadList = new ArrayList<Thread>();
        
        this.lock = lock;
        managerEstadistic = new ManejoEstadistica();
        managerEstadistic.setCantonList(cantonList);
        managerEstadistic.setProvinciasList(provinciasList);
    }
    
    public ManejoEstadistica getManager(){
        return managerEstadistic;
    }

    @Override
    public void run() {
        switch (reader.getFileName()) {
            case "Distelec.txt": 
                cargarLugares(); 
                break;
            case "PADRON_COMPLETO.txt":  
                cargarPadronCompleto();
                  managerEstadistic.adjuntarPersonas();/*---se agrupan */
                  
//                managerEstadistic.crearEstadisticasCantonProvincia();
//                managerEstadistic.crearEstadisticaNacionalGeneros();
//                managerEstadistic.crearEstadisticaNacionalProvincias();
                break;
        }   
    }  

    private void cargarLugares() {
        String valores = " ";
        String[] padron;
        valores = (String) reader.next();
        while (reader.hasNext() && valores != null) {
            try {
                lock.lock();
                if (!valores.equals("")) {
                    padron = valores.split(",");
                    managerEstadistic.addToList(padron[0], padron[1], padron[2]); 
                }
                valores = (String) reader.next();
                lock.unlock();
            } catch (InterruptedException ex) {
                System.out.println("System error");
            } catch (NullPointerException ex) {
                System.out.println("System con null");
            }
        }
    }

    private void cargarPadronCompleto() {
        String valores = " ";
        String[] padron;
        valores = (String) reader.next();
        while (reader.hasNext() && valores != null) {
            try {
                lock.lock();
                if (!valores.equals("")) {
                    padron = valores.split(",");
                    managerEstadistic.addGeneros(padron[1], padron[2]);
                }
                valores = (String) reader.next();
                lock.unlock();
            } catch (InterruptedException ex) {
                System.out.println("System error");
            } catch (NullPointerException ex) {
                System.out.println("System con null");
            }
        }
    }

    public void start() {
        for (int index = 0; index < 2; index++) {
            hilo = new Thread(this, name + index);
            hilo.start();
            threadList.add(hilo);
        }
    }

    public void join() {
        for (int index = 0; index < threadList.size(); index++) {
            try {
                threadList.get(index).join();
            } catch (InterruptedException e) {
                Logger.getLogger(Printer_Results.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    
    public HashMap<String, Canton> getCantonList() {
        return managerEstadistic.getCantonList();
    }
     public HashMap<String, Provincia> getProvinciasList() {
        return managerEstadistic.getProvinciasList();
    }
    
    
}
