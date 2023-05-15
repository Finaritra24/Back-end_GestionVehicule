package com.projetfy.gestionvehicule.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import com.projetfy.gestionvehicule.genericDAO.Connex;
import com.projetfy.gestionvehicule.model.EcheanceVehicule;

public class ServEcheanceVehicule {
    public static boolean ifecheance(String idTypeEcheance,String idvehicule) throws Exception{
        Vector<EcheanceVehicule> list=new EcheanceVehicule().find("SELECT * FROM echeancevehicule where idtypeecheance='"+idTypeEcheance+"' and idvehicule='"+idvehicule+"'", null);
        if(list.size()>=1){return true;}
        return false;
    }
    public static void ajoutEcheanceVehicule(String dateEcheance, String idTypeEcheance, String idVehicule)throws Exception{
            EcheanceVehicule v=new EcheanceVehicule(dateEcheance,idTypeEcheance,idVehicule);
            v.create(v.getNameAuto(), null);
    }
    public static Vector<EcheanceVehicule> filtreListEcheanceVehicule(String idv) throws Exception{
        Vector<EcheanceVehicule> list=new EcheanceVehicule().find("SELECT * FROM echeancevehicule where idvehicule='"+idv+"'", null);
        return list;
    }
    public static void updateEcheanceVehicule(String dh,String ide) throws Exception{
        Connection con=new Connex().getConnection();
        String sql="update echeancevehicule set dateecheance='"+dh+"' where idecheancevehicule='"+ide+"'";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.executeUpdate();
        if(con!=null || stmt!=null ){
            con.close();
            stmt.close();
        }
    }
    public static Vector<EcheanceVehicule> listprofiltypeecheance(String idt) throws Exception{
        Vector<EcheanceVehicule> list=new EcheanceVehicule().find("SELECT * FROM echeancevehicule where idtypeecheance='"+idt+"'", null);
        return list;
    }
    public static void main(String[] args) throws Exception {
           Vector<EcheanceVehicule>list=listprofiltypeecheance("TEC1");
           for(EcheanceVehicule ev:list){
            System.out.println(ev);
           }
    }
}
