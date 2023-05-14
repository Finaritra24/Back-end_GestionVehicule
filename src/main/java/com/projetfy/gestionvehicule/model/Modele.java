package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Modele extends DBTable {
    private String idModele;
    private String nom;

    public Modele() {}

    public Modele(String nom) {
        this.nom = nom;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNameAuto() {
        return "MDL";
    }

    public String getSeqName() {
        return "smodele";
    }

    public String toString() {
        return "Modele{" +
                "idModele='" + idModele + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
