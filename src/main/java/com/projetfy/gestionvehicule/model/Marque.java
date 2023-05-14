package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Marque extends DBTable {
    private String idMarque;
    private String nom;

    public Marque() {}

    public Marque(String nom) {
        this.nom = nom;
    }

    public String getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(String idMarque) {
        this.idMarque = idMarque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNameAuto() {
        return "MRQ";
    }

    public String getSeqName() {
        return "smarque";
    }

    public String toString() {
        return "Marque{" +
                "idMarque='" + idMarque + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
} 
