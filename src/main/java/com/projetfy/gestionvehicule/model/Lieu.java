package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Lieu extends DBTable {
    private String idLieu;
    private String nom;

    public Lieu() {}

    public Lieu(String nom) {
        this.nom = nom;
    }

    public String getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(String idLieu) {
        this.idLieu = idLieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNameAuto() {
        return "LIU";
    }

    public String getSeqName() {
        return "slieu";
    }

    public String toString() {
        return "Lieu{" +
                "idLieu='" + idLieu + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}