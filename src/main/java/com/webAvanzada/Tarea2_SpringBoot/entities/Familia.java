package com.webAvanzada.Tarea2_SpringBoot.entities;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;

@Entity
public class Familia {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idFamilia;

    private String nombreFamilia;

    public Familia(){
        super();
    }

    public Familia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }
}
