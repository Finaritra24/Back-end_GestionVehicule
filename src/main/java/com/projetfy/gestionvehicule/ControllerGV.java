package com.projetfy.gestionvehicule;

import org.springframework.http.HttpHeaders;

import java.util.Map;
import java.util.Vector;

import com.projetfy.gestionvehicule.model.Marque;
import com.projetfy.gestionvehicule.model.Modele;
import com.projetfy.gestionvehicule.model.Type;
import com.projetfy.gestionvehicule.model.Vehicule;
import com.projetfy.gestionvehicule.service.ServAdmin;
import com.projetfy.gestionvehicule.service.ServMarque;
import com.projetfy.gestionvehicule.service.ServModele;
import com.projetfy.gestionvehicule.service.ServVehicule;
import com.projetfy.gestionvehicule.service.ServType;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class ControllerGV {


    //administrateur

    @GetMapping("/getId")
    public String getUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    return cookie.getValue();
                }
            }
        }
        return "null"; // Cookie non trouvé
    }

    @PostMapping("/loginAdmin")
    public String testLogin(@RequestBody Map<String, String> loginData, HttpServletResponse response) throws Exception{
        String identification = loginData.get("identification");
        String mdp = loginData.get("mdp");
        boolean btest=new ServAdmin().testLoginAdmin(identification, mdp);
        if (btest) {
            String id=new ServAdmin().getIdAdmin(identification, mdp);
            Cookie cookie = new Cookie("userId", id);
            cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
            response.addCookie(cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
            response.setStatus(HttpServletResponse.SC_OK);
            return "Connexion réussie";
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Informations d'identification incorrectes";
    }
    //fin admin

    //vehicule
    @GetMapping("/listVehicule")
    public Vector<Vehicule> listVehicule() throws Exception{
        Vector<Vehicule> list=new ServVehicule().listVehicule();
        return list;
    }
    @PostMapping("/ajoutVehicule")
    public static String ajoutVehicule(@RequestBody Map<String, String> vdata, HttpServletResponse response) {
        String numero = vdata.get("numero");
        String idmarque = vdata.get("selectedMarque");
        String idmodele = vdata.get("selectedModele");
        String idtype = vdata.get("selectedType");
        ServVehicule sv=new ServVehicule();
        try {
            sv.ajoutVehicule(numero, idmarque, idmodele, idtype);
            response.setStatus(HttpServletResponse.SC_OK);
            return "Ajout réussie";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur ajout vehicule";
    }
    //fin vehicule

    //modele
    @GetMapping("/listModele")
    public Vector<Modele> listModele() throws Exception{
        Vector<Modele> list=new ServModele().listModele();
        return list;
    }
    //fin modele
    //marque
    @GetMapping("/listMarque")
    public Vector<Marque> listMarque() throws Exception{
        Vector<Marque> list=new ServMarque().listMarque();
        return list;
    }
    //fin marque
    //type
    @GetMapping("/listType")
    public Vector<Type> listType() throws Exception{
        Vector<Type> list=new ServType().listType();
        return list;
    }
    //fin type
}
