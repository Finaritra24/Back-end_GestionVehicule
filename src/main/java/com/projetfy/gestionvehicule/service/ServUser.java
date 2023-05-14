package com.projetfy.gestionvehicule.service;

import java.util.Vector;

import com.projetfy.gestionvehicule.model.Utilisateur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

public class ServUser {
    public boolean testLoginUser(String identification, String mdp) throws Exception{
        Vector<Utilisateur> list=new Utilisateur().find("SELECT * FROM utilisateur where identification='"+identification+"' and passwrd='"+mdp+"'", null);
        if(list.size()>=1){return true;}
        return false;
    }

    public String getIdUser(String identification, String mdp) throws Exception{
        Vector<Utilisateur> list=new Utilisateur().find("SELECT * FROM utilisateur where identification='"+identification+"' and passwrd='"+mdp+"'", null);
        String a=list.get(0).getIdUser();
        return a;
    }
    // public Vector<Utilisateur> getListAdmin(HttpServletRequest request) throws Exception{
    //     Cookie[] cookies = request.getCookies();
    //     String userId = null;
    //     if (cookies != null) {
    //         for (Cookie cookie : cookies) {
    //             if (cookie.getName().equals("userId")) {
    //                 userId = cookie.getValue();
    //                 break;
    //             }
    //         }
    //     }
    //     Vector<Utilisateur> list=new Utilisateur().find("SELECT * FROM Utilisateur where idadmin='"+userId+"' and passwrd='"+mdp+"'", null);
    //     return list;
    // }
    public String getCookie(HttpServletRequest request) throws Exception{
        Cookie[] cookies = request.getCookies();
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }
        return userId;
    }
}
