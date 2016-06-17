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
public class Personas {
   private  String codigo,genero;
   private int cantidad;
   private int cantMujeres,cantHombres;
   public Personas(String codigo,int cantidad){
       this.codigo=codigo;
       this.cantidad=cantidad;
   }

   public Personas(int cantMujeres,int cantHombres){
       this.cantHombres=cantHombres;
       this.cantMujeres=cantMujeres;
   }
   
    public int getCantMujeres() {
        return cantMujeres;
    }

    public void setCantMujeres(int cantMujeres) {
        this.cantMujeres = cantMujeres;
    }

    public int getCantHombres() {
        return cantHombres;
    }

    public void setCantHombres(int cantHombres) {
        this.cantHombres = cantHombres;
    }
   
 

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
   
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
