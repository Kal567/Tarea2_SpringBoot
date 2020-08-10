package com.webAvanzada.Tarea2_SpringBoot.entities;

import javax.persistence.*;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    private String nombreRol;

    public Roles(){
        super();
    }

   /* public Integer getIdRol(){
        return idRol;
    }*/

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
