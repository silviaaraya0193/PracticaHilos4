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
import Vista.Hilos_UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Silvia Araya J.
 */
public class ControladorUI implements ActionListener {

    private Hilos_UI ventana;
    private Printer_Results printer2;
    private Printer_Results printer;
    private File_Reader leer;
    private File_Reader reader;
    private String stadisticType;

    public ControladorUI(Hilos_UI ventana/*,HashMap<String,Provincia> provinciasList,HashMap<String,Canton> cantonList*/) {
        this.ventana = ventana;
        stadisticType = "";
        try {
            startReader();
        } catch (IOException ex) {
            Logger.getLogger(ControladorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salir")) {
            System.exit(0);
        } else if (e.getActionCommand().equalsIgnoreCase("Estadistica Nacional por Genero")) {
            printer2.getManager().crearEstadisticaNacionalGeneros();
            ventana.agregarDatos(printer2.getManager().printEstadisticaNacional());
            stadisticType="Nacional";

        } else if (e.getActionCommand().equalsIgnoreCase("Estadistica Genero por Provincias")) {
            printer2.getManager().crearEstadisticaNacionalProvincias();
            ventana.agregarDatos(printer2.getManager().printEstadisticaGeneroProvincia());
            stadisticType="Provincia";

        } else if (e.getActionCommand().equalsIgnoreCase("Estadistica genero por Canton")) {
            printer2.getManager().crearEstadisticasCantonProvincia();
            ventana.agregarDatos(printer2.getManager().printEstadisticaGeneroCanton());
            stadisticType="Canton";
            
        } else if (e.getActionCommand().equalsIgnoreCase("Guardar")) {
            if (ventana.getRbtnJson().isSelected()) {
                switch (stadisticType) {
                    case "Nacional":printer2.getManager().setEstadisticaNacionalToArchivo("JSON");
                        break;
                    case "Canton":printer2.getManager().setEstadisticaGeneroCanton("JSON");
                        break;
                    case "Provincia":printer2.getManager().setEstadisticaGeneroProvincia("JSON");
                        break;
                }
            } else if (ventana.getRbtnTXT().isSelected()) {
                    switch (stadisticType) {
                    case "Nacional":printer2.getManager().setEstadisticaNacionalToArchivo("TXT");
                        break;
                    case "Canton":printer2.getManager().setEstadisticaGeneroCanton("TXT");
                        break;
                    case "Provincia":printer2.getManager().setEstadisticaGeneroProvincia("TXT");
                        break;
                }
            } else if (ventana.getRbtnXML().isSelected()) {
                    switch (stadisticType) {
                    case "Nacional":printer2.getManager().setEstadisticaNacionalToArchivo("XML");
                        break;
                    case "Canton":printer2.getManager().setEstadisticaGeneroCanton("XML");
                        break;
                    case "Provincia":printer2.getManager().setEstadisticaGeneroProvincia("XML");
                        break;
                }
            }
        }
    }

    private void startReader() throws IOException {
        reader = new File_Reader("Distelec.txt");
        leer = new File_Reader("PADRON_COMPLETO.txt");
        Lock lock = new Lock();

        /*----------------------------1er HILO----------------------------*/
        printer = new Printer_Results(reader, "Hilo #", lock);
        printer.start();
        printer.join();

        /*----------------------------2do HILO-----------------------------*/
        printer2 = new Printer_Results(leer, "Hilo2 #", lock, printer.getProvinciasList(), printer.getCantonList());
        printer2.start();
        printer2.join();
    }
}
