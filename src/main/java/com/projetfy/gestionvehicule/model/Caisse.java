package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;
import com.projetfy.gestionvehicule.genericDAO.AnnotInsert;

public class Caisse extends DBTable {
    private String idCaisse;
    private Double montant;

    public Caisse() {}

    public Caisse(Double montant) {
        this.montant = montant;
    }

    public String getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(String idCaisse) {
        this.idCaisse = idCaisse;
    }

    public Double getNom() {
        return montant;
    }

    public void setNom(Double montant) {
        this.montant = montant;
    }

    public String getNameAuto() {
        return "CSS";
    }

    public String getSeqName() {
        return "scaisse";
    }

    public String toString() {
        return "Caisse{" +
                "idCaisse='" + idCaisse + '\'' +
                ", montant='" + montant + '\'' +
                '}';
    }
} 
