package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.Modele;

public class ServModele{
    public static Vector<Modele> listModele() throws Exception{
        Vector<Modele> list=new Modele().find("SELECT * FROM modele", null);
        return list;
    }
    public static void main(String[]args) throws Exception{
        Vector<Modele> l=listModele();
        System.out.println(l);
    }
}