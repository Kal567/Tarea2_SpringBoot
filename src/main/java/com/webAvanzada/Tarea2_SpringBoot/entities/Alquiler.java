package com.webAvanzada.Tarea2_SpringBoot.entities;

import com.sun.istack.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Alquiler {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idAlquiler;

    private int idProductoAlquiler;
    private String nombreProductoAlquiler;

    //@OneToOne
    private int idUsuarioAlquiler;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAlquiler;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEntregaEstablecida;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRealEntregado;

    private int diasAlquilado;
    private boolean Entregado;
    private double precioProducto;
    private double precioPorDias;

    private int idFactura;

    public Alquiler(){
        super();
    }

    public Alquiler(int idProductoAlquiler, String nombreProductoAlquiler,
                    int idUsuarioAlquiler, LocalDate fechaAlquiler,
                    LocalDate fechaEntregaEstablecida, LocalDate fechaRealEntregado,
                    int diasAlquilado, boolean entregado, double precioProducto,
                    double precioPorDias, int idFactura) {
        this.idProductoAlquiler = idProductoAlquiler;
        this.nombreProductoAlquiler = nombreProductoAlquiler;
        this.idUsuarioAlquiler = idUsuarioAlquiler;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaEntregaEstablecida = fechaEntregaEstablecida;
        this.fechaRealEntregado = fechaRealEntregado;
        this.diasAlquilado = diasAlquilado;
        this.Entregado = entregado;
        this.precioProducto = precioProducto;
        this.precioPorDias = precioPorDias;
        this.idFactura = idFactura;
    }

    public Alquiler(int idProducto, int idUser, Date fechaAlquiler, Date fechaEntregaEstablecida, Date fechaRealEntregado, int diasAlquilado, boolean entregado, double precio, double precioPorDias, Integer integer) {
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public String getNombreProductoAlquiler() {
        return nombreProductoAlquiler;
    }

    public void setNombreProductoAlquiler(String nombreProductoAlquiler) {
        this.nombreProductoAlquiler = nombreProductoAlquiler;
    }

    public int getIdProductoAlquiler() {
        return idProductoAlquiler;
    }

    public void setIdProductoAlquiler(int idProductoAlquiler) {
        this.idProductoAlquiler = idProductoAlquiler;
    }

    public int getIdUsuarioAlquiler() {
        return idUsuarioAlquiler;
    }

    public void setIdUsuarioAlquiler(int idUsuarioAlquiler) {
        this.idUsuarioAlquiler = idUsuarioAlquiler;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDate fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public LocalDate getFechaEntregaEstablecida() {
        return fechaEntregaEstablecida;
    }

    public void setFechaEntregaEstablecida(LocalDate fechaEntregaEstablecida) {
        this.fechaEntregaEstablecida = fechaEntregaEstablecida;
    }

    public LocalDate getFechaRealEntregado() {
        return fechaRealEntregado;
    }

    public void setFechaRealEntregado(LocalDate fechaRealEntregado) {
        this.fechaRealEntregado = fechaRealEntregado;
    }

    public int getDiasAlquilado() {
        return diasAlquilado;
    }

    public void setDiasAlquilado(int diasAlquilado) {
        this.diasAlquilado = diasAlquilado;
    }

    public boolean isEntregado() {
        return Entregado;
    }

    public void setEntregado(boolean entregado) {
        Entregado = entregado;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public double getPrecioPorDias() {
        return precioPorDias;
    }

    public void setPrecioPorDias(double precioPorDias) {
        this.precioPorDias = precioPorDias;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
}
