package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.Trajet;
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
    public static boolean testDispoVehicule(String idv) throws Exception{
        Vector<Trajet> list=new Trajet().find("select*from trajet where idvehicule='"+idv+"' and dhdeb <= CURRENT_DATE AND dhfin >= CURRENT_DATE", null);
        if(list.size()>=1){return true;}
        return false;
    }
    public static void main(String[] args) throws Exception{
        // ajoutVehicule("8080TDA","MRQ1","MDL1","TYP1");
        System.out.print(testDispoVehicule("VCL6"));
    }
}
