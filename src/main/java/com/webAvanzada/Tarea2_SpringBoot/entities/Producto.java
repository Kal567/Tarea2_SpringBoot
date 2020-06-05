package com.webAvanzada.Tarea2_SpringBoot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Producto {
    @Id
    @GeneratedValue
    private int idProducto;

    private double precio;
    private int cantidadActual;
    private boolean disponible;
    //private String familia;
    //private String subFamilia;

    public Producto(){
        super();
    }

    public Producto(double precio, int cantidadActual, boolean disponible) {
        this.precio = precio;
        this.cantidadActual = cantidadActual;
        this.disponible = disponible;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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
}
