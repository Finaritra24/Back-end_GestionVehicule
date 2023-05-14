package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class TypeEcheance extends DBTable {
    private String idTypeEcheance;
    private String nom;

    public TypeEcheance() {}

    public TypeEcheance(String nom) {
        this.nom = nom;
    }

    public String getIdTypeEcheance() {
        return idTypeEcheance;
    }

    public void setIdTypeEcheance(String idTypeEcheance) {
        this.idTypeEcheance = idTypeEcheance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNameAuto() {
        return "TEC";
    }

    public String getSeqName() {
        return "stypeecheance";
    }

    public String toString() {
        return "TypeEcheance{" +
                "idTypeEcheance='" + idTypeEcheance + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
