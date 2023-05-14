package com.projetfy.gestionvehicule.model;
import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Chauffeur extends DBTable {
    private String idChauffeur;
    private String nom;
    private String prenom;
    private String datenaissance;
    private String adresse;
    private String contact;

    public String getNameAuto(){
        return "CFR";
    }

    public Chauffeur(String idChauffeur, String nom, String prenom, String datenaissance, String adresse, String contact) {
        this.idChauffeur = idChauffeur;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.contact = contact;
    }

    public Chauffeur(String nom, String prenom, String datenaissance, String adresse, String contact) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.contact = contact;
    }

    public Chauffeur() {}

    public String getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(String idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSeqName() {
        return "schauffeur";
    }

    @Override
    public String toString() {
        return "Chauffeur{" +
                "idChauffeur='" + idChauffeur + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", datenaissance='" + datenaissance + '\'' +
                ", adresse='" + adresse + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
