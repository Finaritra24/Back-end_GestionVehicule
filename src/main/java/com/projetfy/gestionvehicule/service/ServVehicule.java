package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.Vehicule;

public class ServVehicule{
    public Vector<Vehicule> listVehicule() throws Exception{
        Vector<Vehicule> list=new Vehicule().find("SELECT * FROM vehicule", null);
        return list;
    }
    public static void ajoutVehicule(String numero,String idmarque,String idmodele,String idtype) throws Exception{
        Vehicule v=new Vehicule(numero,idmarque,idmodele,idtype);
        v.create(v.getNameAuto(), null);
    }
    public static void main(String[] args) throws Exception{
        ajoutVehicule("8080TDA","MRQ1","MDL1","TYP1");
    }
}
