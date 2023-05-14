package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class ElementMaintenance extends DBTable {
    private String idElementMaintenance;
    private Vehicule vehicule;
    private Maintenance maintenance;
    private double quantite;

    public ElementMaintenance() {}

    public ElementMaintenance(Vehicule vehicule, Maintenance maintenance, double quantite) {
        this.vehicule = vehicule;
        this.maintenance = maintenance;
        this.quantite = quantite;
    }

    public String getIdElementMaintenance() {
        return idElementMaintenance;
    }

    public void setIdElementMaintenance(String idElementMaintenance) {
        this.idElementMaintenance = idElementMaintenance;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getNameAuto() {
        return "EMC";
    }

    public String getSeqName() {
        return "selementmaintenance";
    }

    public String toString() {
        return "ElementMaintenance{" +
                "idElementMaintenance='" + idElementMaintenance + '\'' +
                ", vehicule=" + vehicule +
                ", maintenance=" + maintenance +
                ", quantite=" + quantite +
                '}';
    }
}
