/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Canton;
import Modelo.File_Reader;
import Modelo.Personas;
import Modelo.Provincia;
import Utils.Reader;
import Utils.Writer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

/**
 *
 * @author Silvia Araya J
 */
public class ManejoEstadistica {

    private Map<String, Provincia> provinciasList;
    private Map<String, Canton> cantonList;
    private Map<String, Personas> hombreList;
    private Map<String, Personas> mujerList;
    private Map<String, Integer> estadisticaProvincia;
    private Map<String, Integer> estadisticaCanton;
    private Map<String, Integer> generoNacional;
    private Map<String, Personas> generoProvincial;
    private Map<String, Personas> generoCantonal;
    private Reader reader;
    private Writer write;
    private String provinciaJson;
    private String provinciaTXT;
    private String provinciaXML;
    private String cantonJson;
    private String cantonTXT;
    private String cantonXML;
    private String generoNacionalJson;
    private String generoNacionalXML;
    private String generoNacionTXT;
    private String generoProvincialJson;
    private String generoProvincialXML;
    private String generoProvincialTXT;
    private String generoCantonalJson;
    private String generoCantonalTXT;
    private String generoCantonalXML;

    public ManejoEstadistica() {

        provinciasList = new HashMap<>();
        cantonList = new HashMap<>();
        hombreList = new HashMap<>();
        mujerList = new HashMap<>();
        estadisticaCanton = new HashMap<>();
        estadisticaProvincia = new HashMap<>();
        generoNacional = new HashMap<>();
        generoProvincial = new HashMap<>();
        generoCantonal = new HashMap<>();
        reader = new Reader();
        write = new Writer();
        provinciaJson = "provincia.json";
        provinciaTXT = "provincia.txt";
        provinciaXML = "provincia.xml";
        cantonJson = "canton.json";
        cantonTXT = "canton.txt";
        cantonXML = "canton.xml";
        generoNacionalJson = "generoNacional.json";
        generoNacionalXML = "generoNacional.xml";
        generoNacionTXT = "generoNacional.txt";
        generoProvincialJson = "generoProvincial.json";
        generoProvincialTXT = "generoProvincial.txt";
        generoProvincialXML = "generoProvincial.xml";
        generoCantonalJson = "generoCantonal.json";
        generoCantonalTXT = "generoCantonal.txt";
        generoCantonalXML = "generoCantonal.xml";
    }

    public void addToList(String codElect, String provincia, String canton) {
        provinciasList.put(codElect, new Provincia(codElect, provincia));
        cantonList.put(codElect, new Canton(codElect, canton));
    }

    public void addGeneros(String codElect, String genero) {
        switch (genero) {
            case "1":/*hombres*/
                if (hombreList.containsKey(codElect)) {
                    Personas sujeto = hombreList.get(codElect);
                    sujeto.setCantidad(sujeto.getCantidad() + 1);
                    hombreList.replace(codElect, sujeto);
                } else {
                    hombreList.put(codElect, new Personas(codElect, 1));
                }
                break;
            case "2":/*mujeres*/
                if (mujerList.containsKey(codElect)) {
                    Personas sujeto = mujerList.get(codElect);
                    sujeto.setCantidad(sujeto.getCantidad() + 1);
                    mujerList.put(codElect, sujeto);
                } else {
                    mujerList.put(codElect, new Personas(codElect, 1));
                }
                break;
        }
    }

    public ArrayList printEstadisticaNacional() {
        ArrayList<String> results= new ArrayList<>();
        System.out.println("\n Estadisticas de genero de votantes a nivel nacional \n ");
        results.add("Mujeres: " + generoNacional.get("Mujeres"));
        results.add( "Hombres: " + generoNacional.get("Hombres"));
        return results;
    }
    
    public void setEstadisticaNacionalToArchivo(String fileTipe) {
        switch (fileTipe) {
            case "JSON":write.escribeJSON(generoNacionalJson, generoNacional.toString());
                break;
            case "TXT":write.escribir(generoNacionTXT, generoNacional.toString());
                break;
            case "XML":write.escribeXML(generoNacionalXML, generoNacional);
                break;
        }
        
    }
    
    public ArrayList<String> printEstadisticaGeneroCanton(){
        ArrayList<String> results= new ArrayList<>();
          for (Map.Entry entry : generoCantonal.entrySet()) {
           String key = (String) entry.getKey();
           Personas people= (Personas) entry.getValue();
           results.add("Canton: "+key+"  total Hombres: "+people.getCantHombres()+"\t "
           + "total Mujeres: "+people.getCantMujeres());
           
       }
          return results;
    }
    public void setEstadisticaGeneroCanton(String fileTipe){
        switch(fileTipe){
             case "JSON":write.escribeJSON(generoCantonalJson, generoCantonal.toString());
                break;
            case "TXT":write.escribir(generoCantonalTXT, generoCantonal.toString());
                break;
            case "XML": write.escribeXML(generoCantonalXML, generoCantonal.toString());
                break;   
        }
    }
    
    public ArrayList<String> printEstadisticaGeneroProvincia(){
         ArrayList<String> results= new ArrayList<>();
         for (Map.Entry entry : generoProvincial.entrySet()) {
           String key = (String) entry.getKey();
           Personas people= (Personas) entry.getValue();
           
          results.add("Provincia: "+key+"  total Hombres: "+people.getCantHombres()+"\t "
           + "total Mujeres: "+people.getCantMujeres());
       }
         return results;
    }
    
    public void setEstadisticaGeneroProvincia(String fileTipe){
        switch(fileTipe){
             case "JSON":write.escribeJSON(generoProvincialJson, generoProvincial.toString());
                break;
            case "TXT": write.escribir(generoProvincialTXT, generoProvincial.toString());
                break;
            case "XML":  write.escribeXML(generoProvincialXML, generoProvincial.toString());
                break;   
        }
    }

    public void printPrueba() throws IOException {
        /* for (Map.Entry entry : provinciasList.entrySet()) {
            String key = (String) entry.getKey();
            Provincia valor = (Provincia) entry.getValue();
            System.out.println("Clave: " + key + "  Provincia: " + valor.getProvincia()+" "+
                   " total Personas: "+ valor.getTotalPersonas());
            
        } */
 /*for (Map.Entry entry : mujerList.entrySet()) {
            String key = (String) entry.getKey();
            Personas mae= (Personas)entry.getValue();
            System.out.println("Clave: " + key + "  Codigo de mae: " + mae.getCodigo()+" "+
                   " cantidad hombres: "+ mae.getCantidad());
       } */
//        System.out.println("\n estadisticas de votantes Provincia");
//       for (Map.Entry entry : estadisticaProvincia.entrySet()) {
//           String key = (String) entry.getKey();
//           int cantidad= (int) entry.getValue();
//           write.escribeJSON(provinciaJson, estadisticaProvincia.toString());
////           reader.leeContenidoTXT(provinciaTXT);
//           write.escribir(provinciaTXT, estadisticaProvincia.toString());
//           write.escribeXML(provinciaXML, estadisticaProvincia.toString());
//           System.out.println("Provincia: "+key+"  Cantidad personas: "+cantidad);
//       }
//        System.out.println(" \n estadisticas de votantes Cantones");
//       for (Map.Entry entry : estadisticaCanton.entrySet()) {
//           String key = (String) entry.getKey();
//           int cantidad= (int) entry.getValue();
//           write.escribeJSON(cantonJson, estadisticaCanton.toString());
//           write.escribir(cantonTXT, estadisticaCanton.toString());
//           write.escribeXML(cantonXML, estadisticaCanton.toString());
//           System.out.println("Canton: "+key+"  Cantidad personas: "+cantidad);
//       }

//        System.out.println("\n estadistica de genero por provincias\n");
//        for (Map.Entry entry : generoProvincial.entrySet()) {
//           String key = (String) entry.getKey();
//           Personas people= (Personas) entry.getValue();
//           write.escribeJSON(generoProvincialJson, generoProvincial.toString());
//           write.escribir(generoProvincialTXT, generoProvincial.toString());
//           write.escribeXML(generoProvincialXML, generoProvincial.toString());
//           System.out.println("Provincia: "+key+"  total Hombres: "+people.getCantHombres()+"\t "
//           + "total Mujeres: "+people.getCantMujeres());
//       }
//        System.out.println("\n estadistica de genero por cantones\n");
//        for (Map.Entry entry : generoCantonal.entrySet()) {
//           String key = (String) entry.getKey();
//           Personas people= (Personas) entry.getValue();
//           write.escribeJSON(generoCantonalJson, generoCantonal.toString());
//           write.escribir(generoCantonalTXT, generoCantonal.toString());
//           write.escribeXML(generoCantonalXML, generoCantonal.toString());
//           System.out.println("Canton: "+key+"  total Hombres: "+people.getCantHombres()+"\t "
//           + "total Mujeres: "+people.getCantMujeres());
//       }
    }

    /*-------agrega por genero las personas a los lugares*/
    public void adjuntarPersonas() {
        /*------------recorrer el hashmap------------*/
        for (Map.Entry mapH : hombreList.entrySet()) {
            /*-----------obtengo el codigo del HOMBRE------*/
            String key = (String) mapH.getKey();
            Personas man = (Personas) mapH.getValue();
            if (provinciasList.containsKey(key)) {
                Provincia prov = provinciasList.get(key);
                prov.setCantidadHombres(prov.getCantidadHombres() + man.getCantidad());
                prov.setTotalPersonas(prov.getTotalPersonas() + man.getCantidad());
                /*-------agrega los nuevos datos de la provincia------*/
                provinciasList.put(key, prov);
            }
            if (cantonList.containsKey(key)) {
                Canton canton = cantonList.get(key);
                canton.setCantidadHombres(canton.getCantidadHombres() + man.getCantidad());
                canton.setTotalPersonas(canton.getTotalPersonas() + man.getCantidad());
                /*-------agrega los nuevos datos de la provincia------*/
                cantonList.put(key, canton);
            }
        }
        /*--------------------------Recorrer el hash--------------*/
        for (Map.Entry mapM : mujerList.entrySet()) {
            /*-----------obtengo el codigo de MUJER------*/
            String key = (String) mapM.getKey();
            Personas woman = (Personas) mapM.getValue();
            if (provinciasList.containsKey(key)) {
                Provincia prov = provinciasList.get(key);
                prov.setCantidadMujeres(prov.getCantidadMujeres() + woman.getCantidad());
                prov.setTotalPersonas(prov.getTotalPersonas() + woman.getCantidad());
                /*-------agrega los nuevos datos de la provincia------*/
                provinciasList.put(key, prov);
            }
            if (cantonList.containsKey(key)) {
                Canton canton = cantonList.get(key);
                canton.setCantidadMujeres(canton.getCantidadMujeres() + woman.getCantidad());
                canton.setTotalPersonas(canton.getTotalPersonas() + woman.getCantidad());
                /*-------agrega los nuevos datos de la provincia------*/
                cantonList.put(key, canton);
            }
        }
    }  //fin del 

    public void crearEstadisticasCantonProvincia() {
        for (Map.Entry entry : provinciasList.entrySet()) {
            Provincia valor = (Provincia) entry.getValue();

            if (estadisticaProvincia.containsKey(valor.getProvincia())) {
                estadisticaProvincia.put(valor.getProvincia(),
                        estadisticaProvincia.get(valor.getProvincia()) + valor.getTotalPersonas());
            } else {
                estadisticaProvincia.put(valor.getProvincia(), valor.getTotalPersonas());
            }
        }

        for (Map.Entry entrye : cantonList.entrySet()) {
            Canton value = (Canton) entrye.getValue();

            if (estadisticaCanton.containsKey(value.getCanton())) {
                estadisticaCanton.put(value.getCanton(),
                        estadisticaCanton.get(value.getCanton()) + value.getTotalPersonas());
            } else {
                estadisticaCanton.put(value.getCanton(), value.getTotalPersonas());
            }
        }
    }

    public void crearEstadisticaNacionalGeneros() {
        for (Map.Entry entry : hombreList.entrySet()) {
            Personas man = (Personas) entry.getValue();
            if (generoNacional.containsKey("Hombres")) {
                generoNacional.put("Hombres", generoNacional.get("Hombres") + man.getCantidad());
            } else {
                generoNacional.put("Hombres", man.getCantidad());
            }

        }
        for (Map.Entry entry : mujerList.entrySet()) {
            Personas woman = (Personas) entry.getValue();
            if (generoNacional.containsKey("Mujeres")) {
                generoNacional.put("Mujeres", generoNacional.get("Mujeres") + woman.getCantidad());
            } else {
                generoNacional.put("Mujeres", woman.getCantidad());
            }
        }
    }

    public void crearEstadisticaNacionalProvincias() {
        for (Map.Entry entry : provinciasList.entrySet()) {
            Provincia value = (Provincia) entry.getValue();
            if (generoProvincial.containsKey(value.getProvincia())) {
                Personas person = (Personas) generoProvincial.get(value.getProvincia());
                person.setCantHombres(person.getCantHombres() + value.getCantidadHombres());
                person.setCantMujeres(person.getCantMujeres() + value.getCantidadMujeres());

                generoProvincial.put(value.getProvincia(), person);
            } else {
                generoProvincial.put(value.getProvincia(),
                        new Personas(value.getCantidadMujeres(), value.getCantidadHombres()));
            }
        }
        for (Map.Entry entry : cantonList.entrySet()) {
            Canton value = (Canton) entry.getValue();
            if (generoCantonal.containsKey(value.getCanton())) {
                Personas person = (Personas) generoCantonal.get(value.getCanton());
                person.setCantHombres(person.getCantHombres() + value.getCantidadHombres());
                person.setCantMujeres(person.getCantMujeres() + value.getCantidadMujeres());

                generoCantonal.put(value.getCanton(), person);
            } else {
                generoCantonal.put(value.getCanton(),
                        new Personas(value.getCantidadMujeres(), value.getCantidadHombres()));
            }
        }

        /* for (Map.Entry entry : provinciasList.entrySet()) {
            String nomProv = (String) entry.getKey();
            Provincia prov= (Provincia) entry.getValue(); 
              
        if(generoProvincial.containsKey(nomProv)){
            Personas persona;
            persona= generoProvincial.get(nomProv);
            persona.setCantidadG(persona.getCantidadG()+prov.getCantidadHombres());
            generoProvincial.put(nomProv,persona);
        }else{
            generoProvincial.put(nomProv,new Personas(prov.getCantidadHombres(),"Hombres"));
        }
 
       }for (Map.Entry entry : provinciasList.entrySet()) {
            String nomProv = (String) entry.getKey();
            Provincia prov= (Provincia) entry.getValue(); 
            
          if(generoProvincial.containsKey(nomProv)){
            Personas persona;
            persona= generoProvincial.get(nomProv);
            persona.setCantidadG(persona.getCantidadG()+prov.getCantidadMujeres());
            generoProvincial.put(nomProv,persona);
        }else{
            generoProvincial.put(nomProv,new Personas(prov.getCantidadMujeres(),"Mujeres"));
        }
       } */
    }

    /*---------------------------------GETTERS AND SETTERS---------------------*/
    public HashMap<String, Provincia> getProvinciasList() {
        return (HashMap<String, Provincia>) provinciasList;
    }

    public void setProvinciasList(HashMap<String, Provincia> provinciasList) {
        this.provinciasList = provinciasList;
    }

    public HashMap<String, Canton> getCantonList() {
        return (HashMap<String, Canton>) cantonList;
    }

    public void setCantonList(HashMap<String, Canton> cantonList) {
        this.cantonList = cantonList;
    }
}
