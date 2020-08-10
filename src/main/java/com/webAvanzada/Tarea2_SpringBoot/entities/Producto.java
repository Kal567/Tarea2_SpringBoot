package com.webAvanzada.Tarea2_SpringBoot.entities;

import javax.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idProducto;

    private String nombreProducto;
    private double precio;
    private int cantidadActual;
    private boolean disponible;

    private String subFamilia;

    public Producto(){
        super();
    }

    public Producto(String nombreProducto, double precio, int cantidadActual,
                    boolean disponible/*, byte[] imgProducto*/, String subFamilia) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidadActual = cantidadActual;
        this.disponible = disponible;
        this.subFamilia = subFamilia;
        //this.imgProducto = imgProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
/*
    public byte[] getImgProducto() {
        return imgProducto;
    }

    public void setImgProducto(byte[] imgProducto) {
        this.imgProducto = imgProducto;
    }
*/

    public String getSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(String subFamilia) {
        this.subFamilia = subFamilia;
    }
}
