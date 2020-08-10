package com.webAvanzada.Tarea2_SpringBoot.entities;

import javax.persistence.*;

@Entity
public class SubFamilia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idSubFamilia;

    private String familia;

    private String nameSubFamily;

    public SubFamilia(){
        super();
    }

    public SubFamilia(String familia, String nameSubFamily) {
        this.familia = familia;
        this.nameSubFamily = nameSubFamily;
    }

    public int getIdSubFamilia() {
        return idSubFamilia;
    }

    public void setIdSubFamilia(int idSubFamilia) {
        this.idSubFamilia = idSubFamilia;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getNameSubFamily() {
        return nameSubFamily;
    }

    public void setNameSubFamily(String nameSubFamily) {
        this.nameSubFamily = nameSubFamily;
    }
}
