package com.projetfy.gestionvehicule.model;

import java.sql.Timestamp;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class EcheanceVehicule extends DBTable {
    private String idEcheanceVehicule;
    private Timestamp dateEcheance;
    private String idTypeEcheance;
    private String idVehicule;

    public EcheanceVehicule() {}

    public EcheanceVehicule(Timestamp dateEcheance, String idTypeEcheance, String idVehicule) {
        this.dateEcheance = dateEcheance;
        this.idTypeEcheance = idTypeEcheance;
        this.idVehicule = idVehicule;
    }

    public String getIdEcheanceVehicule() {
        return idEcheanceVehicule;
    }

    public void setIdEcheanceVehicule(String idEcheanceVehicule) {
        this.idEcheanceVehicule = idEcheanceVehicule;
    }

    public Timestamp getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Timestamp dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getIdTypeEcheance() {
        return idTypeEcheance;
    }

    public void setIdTypeEcheance(String idTypeEcheance) {
        this.idTypeEcheance = idTypeEcheance;
    }

    public String getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getNameAuto() {
        return "ECV";
    }

    public String getSeqName() {
        return "secheancevehicule";
    }

    public String toString() {
        return "EcheanceVehicule{" +
                "idEcheanceVehicule='" + idEcheanceVehicule + '\'' +
                ", dateEcheance=" + dateEcheance +
                ", idTypeEcheance='" + idTypeEcheance + '\'' +
                ", idVehicule='" + idVehicule + '\'' +
                '}';
    }
}
