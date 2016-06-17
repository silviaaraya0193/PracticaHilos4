/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author viccr
 */
public class Canton {
    private String codElect,canton;
    private int cantidadMujeres,cantidadHombres,totalPersonas;
    
    public Canton(String codElec,String canton){
    this.canton=canton;
    this.codElect=codElec;
      } 
    public Canton(){}
    public int getCantidadMujeres() {
        return cantidadMujeres;
    }

    public void setCantidadMujeres(int cantidadMujeres) {
        this.cantidadMujeres = cantidadMujeres;
    }

    public int getCantidadHombres() {
        return cantidadHombres;
    }

    public void setCantidadHombres(int cantidadHombres) {
        this.cantidadHombres = cantidadHombres;
    }

    public int getTotalPersonas() {
        return totalPersonas;
    }

    public void setTotalPersonas(int totalPersonas) {
        this.totalPersonas = totalPersonas;
    }

    public String getCodElect() {
        return codElect;
    }

    public void setCodElect(String codElect) {
        this.codElect = codElect;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }
    
}
