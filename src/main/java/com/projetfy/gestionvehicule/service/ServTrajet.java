package com.projetfy.gestionvehicule.service;

import com.projetfy.gestionvehicule.model.Trajet;

public class ServTrajet {
    public static void ajoutTrajet( String dhDeb, String dhFin, String lieuDeb, String lieuFin, double kmDeb, double kmFin, double qteCarb, double montantCar, String motif, String idVehicule, String idChauffeur, double vitesse)throws Exception{
        Trajet t=new Trajet(  dhDeb,  dhFin,  lieuDeb,  lieuFin,  kmDeb,  kmFin,  qteCarb,  montantCar,  motif,  idVehicule,  idChauffeur,  vitesse);
        t.create(t.getNameAuto(), null);
    }
    public static void main(String[]args) throws Exception{
        ajoutTrajet("2023-05-11 08:00:00", "2023-05-11 17:00:00", "LIU1", "LIU2", 100.5, 300.2, 50.0, 70.0, "Rendez-vous client", "VCL1", "CFR1", 120.0);
    }
}
