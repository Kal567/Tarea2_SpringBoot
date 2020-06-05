package com.webAvanzada.Tarea2_SpringBoot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.util.List;

@Entity
public class Factura {
    @Id
    @GeneratedValue
    private int idFactura;

    private List<Alquiler> productos;
    private Date fechaFacturacion;
    private Usuario usuario;
    private double totalAlquiler;

    public Factura(){
        super();
    }

    public Factura(List<Alquiler> productos, Date fechaFacturacion, Usuario usuario, double totalAlquiler) {
        this.productos = productos;
        this.fechaFacturacion = fechaFacturacion;
        this.usuario = usuario;
        this.totalAlquiler = totalAlquiler;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public List<Alquiler> getProductos() {
        return productos;
    }

    public void setProductos(List<Alquiler> productos) {
        this.productos = productos;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getTotalAlquiler() {
        return totalAlquiler;
    }

    public void setTotalAlquiler(double totalAlquiler) {
        this.totalAlquiler = totalAlquiler;
    }
}
