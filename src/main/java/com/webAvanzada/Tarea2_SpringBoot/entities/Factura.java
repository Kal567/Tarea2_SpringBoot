package com.webAvanzada.Tarea2_SpringBoot.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idFactura;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFacturacion;

    private int idUsuario;

    private int idAlquilerFacturado;
    private double totalAlquiler;

    public Factura(){
        super();
    }

    public Factura(/*LocalDate now,*/ int usuario, double totalAlquiler, int idAlquilerFacturado) {
        //this.productosAlquilados = productosAlquilados;
        this.fechaFacturacion = LocalDate.now();
        this.idUsuario = usuario;
        this.totalAlquiler = totalAlquiler;
        this.idAlquilerFacturado = idAlquilerFacturado;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdAlquilerFacturado() {
        return idAlquilerFacturado;
    }

    public void setIdAlquilerFacturado(int idAlquilerFacturado) {
        this.idAlquilerFacturado = idAlquilerFacturado;
    }

    /*
        public List<Alquiler> getProductosAlquilados() {
            return productosAlquilados;
        }

        public void setProductosAlquilados(List<Alquiler> productosAlquilados) {
            this.productosAlquilados = productosAlquilados;
        }

        public void addProductosAlquilados(Alquiler productosAlquilados){
            this.productosAlquilados.add(productosAlquilados);
        }
    */
    public LocalDate getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(LocalDate fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotalAlquiler() {
        return totalAlquiler;
    }

    public void setTotalAlquiler(double totalAlquiler) {
        this.totalAlquiler = totalAlquiler;
    }

}
