package com.webAvanzada.Tarea2_SpringBoot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Alquiler {
    @Id
    @GeneratedValue
    private int idAlquiler;

    private Producto producto;
    private Usuario cliente;
    private Date fechaAlquiler;
    private Date fechaEntrega;
    private boolean Entregado;
    private double precioPorDias;

    public Alquiler(){
        super();
    }

    public Alquiler(Producto producto, Usuario cliente, Date fechaAlquiler, Date fechaEntrega, boolean entregado, double precioPorDias) {
        this.producto = producto;
        this.cliente = cliente;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaEntrega = fechaEntrega;
        Entregado = entregado;
        this.precioPorDias = precioPorDias;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public boolean isEntregado() {
        return Entregado;
    }

    public void setEntregado(boolean entregado) {
        Entregado = entregado;
    }

    public double getPrecioPorDias() {
        return precioPorDias;
    }

    public void setPrecioPorDias(double precioPorDias) {
        this.precioPorDias = precioPorDias;
    }
}
