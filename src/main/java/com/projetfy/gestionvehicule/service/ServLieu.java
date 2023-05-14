package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.Lieu;

public class ServLieu{
    public static Vector<Lieu> listLieu() throws Exception{
        Vector<Lieu> list=new Lieu().find("SELECT * FROM lieu", null);
        return list;
    }
    public static void main(String[]args) throws Exception{
        Vector<Lieu> l=listLieu();
        System.out.println(l);
    }
}