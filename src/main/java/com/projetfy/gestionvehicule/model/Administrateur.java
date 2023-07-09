package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Administrateur extends DBTable {
    private String idAdmin;
    private String identification;
    private String passwrd;

    public Administrateur() {}

    public Administrateur(String idAdmin,String identification, String passwrd) {
        this.idAdmin=idAdmin;
        this.identification = identification;
        this.passwrd = passwrd;
    }
    public Administrateur(String identification, String passwrd) {
        this.identification = identification;
        this.passwrd = passwrd;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public String getNameAuto() {
        return "ADM";
    }

    public String getSeqName() {
        return "sadmin";
    }

    public String toString() {
        return "Administrateur{" +
                "idAdmin='" + idAdmin + '\'' +
                ", identification='" + identification + '\'' +
                ", passwrd='" + passwrd + '\'' +
                '}';
    }
}
