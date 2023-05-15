package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.TypeEcheance;

public class ServTypeEcheance {
    public static Vector<TypeEcheance> listTypeEcheance() throws Exception{
        Vector<TypeEcheance> list=new TypeEcheance().find("SELECT * FROM typeecheance", null);
        return list;
    }
    public static void main(String[]args) throws Exception{
        Vector<TypeEcheance> l=listTypeEcheance();
        System.out.println(l);
    }
}
