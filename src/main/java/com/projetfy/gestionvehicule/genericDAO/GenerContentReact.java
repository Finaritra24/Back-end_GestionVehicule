package com.projetfy.gestionvehicule.genericDAO;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.projetfy.gestionvehicule.model.Trajet;

public class GenerContentReact {
    public static ArrayList<String> contentInsert(Class<?> c){
        Field[] fields=c.getDeclaredFields();
        ArrayList<String> listContent=new ArrayList<>();
        for(Field f:fields){
            Class<?> cAttribut=f.getType();
            String nameClassAttribut=cAttribut.getName();
            listContent.add(nameClassAttribut);
         }
        return listContent;
    }
    public String myContent(Field f){
        String type=f.getType().getName();
        if(type.equals("java.lang.String")){
            String values="<div className=\"flex flex-wrap justify-content-center align-items-center gap-2\"><label htmlFor=\"username\" className=\"w-6rem\">Numero</label><InputText value={numero} onChange={(e) => setNumero(e.target.value)} placeholder=\"Enter a numero\"/></div>";
        }
        return "";
    }
    public static void main(String[]args){
        Trajet trajet=new Trajet();
        Class<?> c=trajet.getClass();
        ArrayList<String> list=contentInsert(c);
        for(String s:list){
            System.out.println(s);
        }
    }
}
