/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Silvia Araya J.
 */
public class Provincia {

    String codElect, provincia;
    private int cantidadMujeres,cantidadHombres,totalPersonas;

    public Provincia(String codElec, String provincia) {
        this.provincia = provincia;
        this.codElect = codElec;
    }

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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}
