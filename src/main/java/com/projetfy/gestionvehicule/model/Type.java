package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Type extends DBTable {
    private String idType;
    private String nom;

    public Type() {}

    public Type(String nom) {
        this.nom = nom;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNameAuto() {
        return "TYP";
    }

    public String getSeqName() {
        return "stype";
    }

    public String toString() {
        return "Type{" +
                "idType='" + idType + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
