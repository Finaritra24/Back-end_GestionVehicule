package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.Type;

public class ServType{
    public static Vector<Type> listType() throws Exception{
        Vector<Type> list=new Type().find("SELECT * FROM type", null);
        return list;
    }
    public static void main(String[]args) throws Exception{
        Vector<Type> l=listType();
        System.out.println(l);
    }
}