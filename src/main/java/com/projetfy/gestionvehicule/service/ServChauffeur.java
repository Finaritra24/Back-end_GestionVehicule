package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.Chauffeur;

public class ServChauffeur{
    public static Vector<Chauffeur> listChauffeur() throws Exception{
        Vector<Chauffeur> list=new Chauffeur().find("SELECT * FROM chauffeur", null);
        return list;
    }
    public static void main(String[]args) throws Exception{
        Vector<Chauffeur> l=listChauffeur();
        System.out.println(l);
    }
}