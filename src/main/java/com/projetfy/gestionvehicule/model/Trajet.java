package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Trajet extends DBTable {
    private String idTrajet;
    private String dhDeb;
    private String dhFin;
    private String lieuDeb;
    private String lieuFin;
    private Double kmDeb;
    private Double kmFin;
    private Double qteCarb;
    private Double montantCar;
    private String motif;
    private String idVehicule;
    private String idChauffeur;
    private Double vitesse;

    public Trajet() {}
    public Trajet(String idTrajet, String dhDeb, String dhFin, String lieuDeb, String lieuFin, Double kmDeb, Double kmFin, Double qteCarb, Double montantCar, String motif, String idVehicule, String idChauffeur, Double vitesse) {
        this.idTrajet = idTrajet;
        this.dhDeb = dhDeb;
        this.dhFin = dhFin;
        this.lieuDeb = lieuDeb;
        this.lieuFin = lieuFin;
        this.kmDeb = kmDeb;
        this.kmFin = kmFin;
        this.qteCarb = qteCarb;
        this.montantCar = montantCar;
        this.motif = motif;
        this.idVehicule = idVehicule;
        this.idChauffeur = idChauffeur;
        this.vitesse = vitesse;
    }
    public Trajet( String dhDeb, String dhFin, String lieuDeb, String lieuFin, Double kmDeb, Double kmFin, Double qteCarb, Double montantCar, String motif, String idVehicule, String idChauffeur, Double vitesse) {
        this.dhDeb = dhDeb;
        this.dhFin = dhFin;
        this.lieuDeb = lieuDeb;
        this.lieuFin = lieuFin;
        this.kmDeb = kmDeb;
        this.kmFin = kmFin;
        this.qteCarb = qteCarb;
        this.montantCar = montantCar;
        this.motif = motif;
        this.idVehicule = idVehicule;
        this.idChauffeur = idChauffeur;
        this.vitesse = vitesse;
    }
    public String getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(String idTrajet) {
        this.idTrajet = idTrajet;
    }

    public String getDhDeb() {
        return dhDeb;
    }

    public void setDhDeb(String dhDeb) {
        this.dhDeb = dhDeb;
    }

    public String getDhFin() {
        return dhFin;
    }

    public void setDhFin(String dhFin) {
        this.dhFin = dhFin;
    }

    public String getLieuDeb() {
        return lieuDeb;
    }

    public void setLieuDeb(String lieuDeb) {
        this.lieuDeb = lieuDeb;
    }

    public String getLieuFin() {
        return lieuFin;
    }

    public void setLieuFin(String lieuFin) {
        this.lieuFin = lieuFin;
    }

    public Double getKmDeb() {
        return kmDeb;
    }

    public void setKmDeb(Double kmDeb) {
        this.kmDeb = kmDeb;
    }

    public Double getKmFin() {
        return kmFin;
    }

    public void setKmFin(Double kmFin) {
        this.kmFin = kmFin;
    }

    public Double getQteCarb() {
        return qteCarb;
    }

    public void setQteCarb(Double qteCarb) {
        this.qteCarb = qteCarb;
    }

    public Double getMontantCar() {
        return montantCar;
    }

    public void setMontantCar(Double montantCar) {
        this.montantCar = montantCar;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(String idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public Double getVitesse() {
        return vitesse;
    }

    public void setVitesse(Double vitesse) {
        this.vitesse = vitesse;
    }

    public String getNameAuto() {
        return "TRJ";
    }

    @Override
    public String getSeqName() {
        return "strajet";
    }

    @Override
public String toString() {
    return "Trajet{" +
            "idTrajet='" + idTrajet + '\'' +
            ", DHDeb=" + dhDeb +
            ", DHFin=" + dhFin +
            ", lieuDeb='" + lieuDeb + '\'' +
            ", lieuFin='" + lieuFin + '\'' +
            ", kmDeb=" + kmDeb +
            ", kmFin=" + kmFin +
            ", qteCarb=" + qteCarb +
            ", montantCar=" + montantCar +
            ", motif='" + motif + '\'' +
            ", idVehicule='" + idVehicule + '\'' +
            ", idChauffeur='" + idChauffeur + '\'' +
            ", vitesse=" + vitesse +
            '}';
}

}