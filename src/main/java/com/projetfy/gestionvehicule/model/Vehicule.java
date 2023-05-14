package com.projetfy.gestionvehicule.model;

import com.projetfy.gestionvehicule.genericDAO.DBTable;

public class Vehicule extends DBTable {
    private String idVehicule;
    private String numero;
    private String idMarque;
    private String idModele;
    private String idType;


    public Vehicule() {}

    public Vehicule(String numero, String idMarque, String idModele,String idType) {
        this.numero = numero;
        this.idMarque = idMarque;
        this.idModele = idModele;
        this.idType=idType;
    }
    public Vehicule(String idVehicule,String numero, String idMarque, String idModele,String idType) {
        this.idVehicule=idVehicule;
        this.numero = numero;
        this.idMarque = idMarque;
        this.idModele = idModele;
        this.idType=idType;
    }

    public String getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(String idMarque) {
        this.idMarque = idMarque;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public String getNameAuto() {
        return "VCL";
    }

    public String getSeqName() {
        return "svehicule";
    }
    
	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
    public String toString() {
        return "Vehicule{" +
                "idVehicule='" + idVehicule + '\'' +
                ", numero='" + numero + '\'' +
                ", idMarque='" + idMarque + '\'' +
                ", idModele='" + idModele + '\'' +
                '}';
    }
}