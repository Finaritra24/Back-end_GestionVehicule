package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.Marque;

public class ServMarque{
    public static Vector<Marque> listMarque() throws Exception{
        Vector<Marque> list=new Marque().find("SELECT * FROM marque", null);
        return list;
    }
    public static void main(String[]args) throws Exception{
        Vector<Marque> l=listMarque();
        System.out.println(l);
    }
}