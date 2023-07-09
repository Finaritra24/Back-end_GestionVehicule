package com.projetfy.gestionvehicule.genericDAO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;
import java.util.Vector;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetfy.gestionvehicule.model.Vehicule;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class GenerController {
    //fonction 
    public static int getType(String input) {
        try {
            Integer.parseInt(input);
            return 0;
        } catch (NumberFormatException e1) {
            try {
                Double.parseDouble(input);
                return 1;
            } catch (NumberFormatException e2) {
                return 2;
            }
        }
    }
    @PostMapping("/setCookie-*")

    //Cookie
    public static String setCookieObject(@RequestBody Map<String, String> vdata, HttpServletResponse response,HttpServletRequest request) {
        String id = vdata.get("id");
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/setCookie-",""); // Récupère le chemin de l'URL
        String nameClass="";
        try {
            Class c = Class.forName("com.projetfy.gestionvehicule.model."+path);
            nameClass=c.getName().replace("com.projetfy.gestionvehicule.model.", "");
            Cookie cookie = new Cookie("id"+nameClass, id);
            cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
            response.addCookie(cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
            response.setStatus(HttpServletResponse.SC_OK);
            return "Ajout Cookie réussie,nom cookie=id"+nameClass;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur ajout Cookie";
    }

    public String getCookieObject(HttpServletRequest request,Class c) {
        String nameClass=c.getName().replace("com.projetfy.gestionvehicule.model.", "");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id"+nameClass)) {
                    return cookie.getValue();
                }
            }
        }
        return "erreur, name Cookie: "+nameClass; // Cookie non trouvé
    }
    public int ifInListParameter(String[] lesAttributs,String nom){
        for(String s:lesAttributs){
            if(s.equals(nom)){
                return 1;
            }
        }
        return 0;
    }
    
    //Ajout
    @PostMapping("/ajoutg-*")
    public String ajout(@RequestBody Map<String, String> vdata,HttpServletResponse response,HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/ajoutg-",""); // Récupère le chemin de l'URL
        String nameClass="";
        try {
            Class c = Class.forName("com.projetfy.gestionvehicule.model."+path);
            nameClass=c.getName();
            Object instance = c.newInstance();
            String[] lesAttributs = new String[c.getDeclaredFields().length];
                for (int i = 1; i < c.getDeclaredFields().length; i++) {
                    String myMethod="";
                    try {
                        lesAttributs[i] = c.getDeclaredFields()[i].getName();
                        Class cAttribut=c.getDeclaredFields()[i].getType();
                        String nameTypeAttribut=cAttribut.getTypeName();
                        String value=vdata.get(lesAttributs[i].toLowerCase());
                        myMethod="set" + toUpperCaseFirst(lesAttributs[i]);
                        if(value==null && lesAttributs[i].startsWith("id")){
                            String myIdCookie=getCookieObject(request,c);
                            Method method = c.getMethod(myMethod, String.class);
                            method.invoke(instance, myIdCookie);
                        }
                        else{
                            if(nameTypeAttribut=="java.lang.String"){
                                Method method = c.getMethod(myMethod, String.class);
                                method.invoke(instance, value);
                            }
                            else if(nameTypeAttribut=="java.lang.Double" ){
                                Method method = c.getMethod(myMethod, Double.class);
                                Double v=Integer.parseInt(value)+0.0;
                                method.invoke(instance, v);
                            }
                            else{
                                Method method = c.getMethod(myMethod, Integer.class);
                                int v=Integer.parseInt(value);
                                method.invoke(instance, v);
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                            | NoSuchMethodException | SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        System.out.print(myMethod);
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return "La methode "+myMethod+" n'existe pas" +lesAttributs[i];
                    }
                }
            //2
            Method nameMethode = c.getMethod("getNameAuto");
            String name=(String)nameMethode.invoke(instance);
            Method createMethod = c.getMethod("create", String.class, Connection.class);
            createMethod.invoke(instance, name, null);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "La class "+path+" n'existe pas";
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Erreur ajout "+nameClass;
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }
    public static String toUpperCaseFirst(String str){
        if (str.length() > 0) {
            String firstLetter = str.substring(0, 1).toUpperCase();
            String restOfString = str.substring(1);

            String result = firstLetter + restOfString;
            return result;
        } else {
            System.out.println(str); // Chaîne vide, aucune modification nécessaire
        }
        return str;
    }

    //Liste
    @GetMapping("/listg-*")
    public Vector<?> listObject(HttpServletRequest request) { 
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/listg-",""); // Récupère le chemin de l'URL
        String nameClass="";
        try {
            Class c = Class.forName("com.projetfy.gestionvehicule.model."+path);
            nameClass=c.getName().replace("com.projetfy.gestionvehicule.model.", "");
            Object o=c.newInstance();
                Method method = c.getMethod("find", String.class,Connection.class);
                Connection con=null;
                Vector<?>v=(Vector<?>)method.invoke(o, "select*from "+nameClass,con);
                return v;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public static String myClassName(String url){
        String myClass=url.replace("com.projetfy.gestionvehicule.model.", "");
        int index =myClass.indexOf("_");
        myClass=myClass.replace(myClass.substring(index),"");
        return myClass;
    }
    public static String myFunctionName(String url){
        String myClass=url.replace("com.projetfy.gestionvehicule.model.", "");
        int index =myClass.indexOf("_");
        myClass=myClass.replace(myClass.substring(index),"");
        return myClass;
    }
    public static void main (String[]args) throws Exception{
        Class c = Class.forName("com.projetfy.gestionvehicule.model.Vehicule");
        String nameClass=c.getName().replace("com.projetfy.gestionvehicule.model.","");
        Object o=c.newInstance();
            Method method = c.getMethod("find", String.class,Connection.class);
            Vector<?>v=(Vector<?>)method.invoke(o, "select*from "+nameClass,null);
            for(int i=0;i<v.size();i++){
                System.out.println(v.get(i));
            }
    }
}
