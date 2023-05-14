package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Maintenance extends DBTable {
    private String idMaintenance;
    private String nom;

    public Maintenance() {}

    public Maintenance(String nom) {
        this.nom = nom;
    }

    public String getIdMaintenance() {
        return idMaintenance;
    }

    public void setIdMaintenance(String idMaintenance) {
        this.idMaintenance = idMaintenance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNameAuto() {
        return "MTC";
    }

    public String getSeqName() {
        return "smaintenance";
    }

    public String toString() {
        return "Maintenance{" +
                "idMaintenance='" + idMaintenance + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}