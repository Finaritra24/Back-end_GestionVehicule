package com.projetfy.gestionvehicule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.core.io.ResourceLoader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import com.projetfy.gestionvehicule.model.Chauffeur;
import com.projetfy.gestionvehicule.model.EcheanceVehicule;
import com.projetfy.gestionvehicule.model.Lieu;
import com.projetfy.gestionvehicule.model.Marque;
import com.projetfy.gestionvehicule.model.Modele;
import com.projetfy.gestionvehicule.model.Type;
import com.projetfy.gestionvehicule.model.TypeEcheance;
import com.projetfy.gestionvehicule.model.Vehicule;
import com.projetfy.gestionvehicule.service.ServAdmin;
import com.projetfy.gestionvehicule.service.ServChauffeur;
import com.projetfy.gestionvehicule.service.ServEcheanceVehicule;
import com.projetfy.gestionvehicule.service.ServLieu;
import com.projetfy.gestionvehicule.service.ServUser;
import com.projetfy.gestionvehicule.service.ServMarque;
import com.projetfy.gestionvehicule.service.ServModele;
import com.projetfy.gestionvehicule.service.ServTrajet;
import com.projetfy.gestionvehicule.service.ServVehicule;

import freemarker.template.Configuration;
import freemarker.template.Template;

import com.projetfy.gestionvehicule.service.ServType;
import com.projetfy.gestionvehicule.service.ServTypeEcheance;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class ControllerGV {
    //test
    @Autowired
    private Configuration freemarkerConfig;

    @GetMapping("/myAjoutAffiche")
    public String myAjoutAffiche(Model model) {
        String stateDeclarations = "const [numero, setNumero] = useState('');";
        String effectDeclarations = "function handleSubmit(event) {event.preventDefault();console.log(marque);}"; 

        model.addAttribute("stateDeclarations", stateDeclarations);
        model.addAttribute("effectDeclarations", effectDeclarations);

        return "ajout";
    }

    
    @GetMapping("/ajoutComponent")
    @ResponseBody
    public ResponseEntity<String> getAjoutComponent() {
        try {
            Resource resource = new ClassPathResource("static/Ajout.jsx");
            String content = new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);
            String stringStateDeclarations = "const [marque, setMarque] = useState('');\n";
            String stringEffectDeclarations = "const handleSubmit = event => { \n event.preventDefault(); \n console.log(marque); \n };";
    
            // Remplacer les balises spéciales par les valeurs réelles
            content = content.replace("{stateDeclarations}", stringStateDeclarations);
            content = content.replace("{effectDeclarations}", stringEffectDeclarations);
    
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(content);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    

    //administrateur

    @GetMapping("/getAdminId")
    public String getAdminId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("adminId")) {
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
            Cookie cookie = new Cookie("adminId", id);
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
    @GetMapping("/getVehiculeId")
    public static String getVehiculeId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("idvehicule")) {
                    return cookie.getValue();
                }
            }
        }
        return "null"; // Cookie non trouvé
    }
    @PostMapping("/testDispoVehicule")
    public void testDispoVehicule(@RequestBody Map<String, String> loginData, HttpServletResponse response) throws Exception {
    String identification = loginData.get("idv");
    boolean btest = new ServVehicule().testDispoVehicule(identification);

    if (btest) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Véhicule disponible");
    } else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
    }

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
    @PostMapping("/profilVehicule")
    public static String profilVehicule(@RequestBody Map<String, String> vdata, HttpServletResponse response) {
        String id = vdata.get("id");
        try {
            Cookie cookie = new Cookie("idvehicule", id);
            cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
            response.addCookie(cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
            response.setStatus(HttpServletResponse.SC_OK);
            return "Ajout réussie";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur vers profil";
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
    //typeecheance
    @GetMapping("/listTypeEcheance")
    public Vector<TypeEcheance> listTypeEcheance() throws Exception{
        Vector<TypeEcheance> list=new ServTypeEcheance().listTypeEcheance();
        return list;
    }
    @PostMapping("/setCookieTypeEcheance")
    public static String setCookieTypeEnchere(@RequestBody Map<String, String> vdata, HttpServletResponse response) {
        String id = vdata.get("id");
        try {
            Cookie cookie = new Cookie("idtypeecheance", id);
            cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
            response.addCookie(cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
            response.setStatus(HttpServletResponse.SC_OK);
            return "Ajout réussie";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur vers  echeance";
    }
    @GetMapping("/getTypeEcheanceId")
    public String getTypeEcheanceId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("idtypeecheance")) {
                    return cookie.getValue();
                }
            }
        }
        return "null"; // Cookie non trouvé
    }
    //fin typeecheance
    //marque
    @GetMapping("/listLieu")
    public Vector<Lieu> listLieu() throws Exception{
        Vector<Lieu> list=new ServLieu().listLieu();
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
    //marque
    @GetMapping("/listChauffeur")
    public Vector<Chauffeur> listChauffeur() throws Exception{
        Vector<Chauffeur> list=new ServChauffeur().listChauffeur();
        return list;
    }
    //fin marque

    //user
    @PostMapping("/ifMdpUser")
    public String ifMdpUser(@RequestBody Map<String, String> loginData, HttpServletResponse response,HttpServletRequest request) throws Exception{
        String mdp = loginData.get("mdp");
        String id=getUserId(request);
        boolean btest=new ServUser().ifMdp(id, mdp);
        if (btest) {
            response.setStatus(HttpServletResponse.SC_OK);
            return "Connexion réussie";
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Informations d'identification incorrectes";
    }
    @PostMapping("/loginUser")
    public String testUser(@RequestBody Map<String, String> loginData, HttpServletResponse response) throws Exception{
        String identification = loginData.get("identification");
        String mdp = loginData.get("mdp");
        boolean btest=new ServUser().testLoginUser(identification, mdp);
        if (btest) {
            String id=new ServUser().getIdUser(identification, mdp);
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
    @GetMapping("/getUserId")
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
    //fin user

    //trajet
    @PostMapping("/ajoutTrajet")
    public static String ajoutTrajet(@RequestBody Map<String, String> vdata, HttpServletResponse response) {
         String dhDeb=vdata.get("dhdeb");
         String dhFin=vdata.get("dhfin");
         String lieuDeb=vdata.get("selectedLieuDeb");
         String lieuFin=vdata.get("selectedLieuFin");
         double kmDeb=Double.parseDouble(vdata.get("kmdeb"));
         double kmFin=Double.parseDouble(vdata.get("kmfin"));
         double qteCarb=Double.parseDouble(vdata.get("qteCarb"));
         double montantCar=Double.parseDouble(vdata.get("montantCar"));
         String motif=vdata.get("motif");
         String idVehicule=vdata.get("selectedVehicule");
         String idChauffeur=vdata.get("selectedChauffeur");
         double vitesse=Double.parseDouble(vdata.get("vitesse"));
        ServTrajet st=new ServTrajet();
        try {
            st.ajoutTrajet(  dhDeb,  dhFin,  lieuDeb,  lieuFin,  kmDeb,  kmFin,  qteCarb,  montantCar,  motif,  idVehicule,  idChauffeur,  vitesse);
            response.setStatus(HttpServletResponse.SC_OK);
            return "Ajout réussie";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur ajout trajet";
    }
    //fintrajet
    //echeance vehicule
    @PostMapping("/ajoutAssurance")
    public static String ajoutEcheanceVehicule(@RequestBody Map<String, String> vdata, HttpServletResponse response,HttpServletRequest request) throws Exception {
        String de = vdata.get("dateecheance");
        String st = vdata.get("selectedType");
        String idv=getVehiculeId(request);
        ServEcheanceVehicule sv=new ServEcheanceVehicule();
        if(!sv.ifecheance(st, idv)) {
            sv.ajoutEcheanceVehicule(de, st,idv);
            response.setStatus(HttpServletResponse.SC_OK);
            return "Ajout réussie";
        } 
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur ajout echeance vehicule";
    }
    @GetMapping("/listAssurance")
    public Vector<EcheanceVehicule> listEcheanceVehicule(HttpServletRequest request) throws Exception{
        String idv=getVehiculeId(request);
        Vector<EcheanceVehicule> list=new ServEcheanceVehicule().filtreListEcheanceVehicule(idv);
        return list;
    }
    @GetMapping("/listProfilTypeAssurance")
    public Vector<EcheanceVehicule> listProfilTypeAssurance(HttpServletRequest request) throws Exception{
        String idv=getTypeEcheanceId(request);
        Vector<EcheanceVehicule> list=new ServEcheanceVehicule().filtreListEcheanceVehicule(idv);
        return list;
    }
    @PostMapping("/cookiModifEcheance")
    public static String cookiModifEcheance(@RequestBody Map<String, String> vdata, HttpServletResponse response) {
        String id = vdata.get("id");
        try {
            Cookie cookie = new Cookie("idecheance", id);
            cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
            response.addCookie(cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
            response.setStatus(HttpServletResponse.SC_OK);
            return "Ajout réussie";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur vers profil";
    }
    @PostMapping("/modifEcheance")
    public static String modifEcheance(@RequestBody Map<String, String> vdata, HttpServletResponse response,HttpServletRequest request) {
        String date = vdata.get("dateecheance");
        try {
            String ide=getEcheanceId(request);
            new ServEcheanceVehicule().updateEcheanceVehicule(date, ide);
            response.setStatus(HttpServletResponse.SC_OK);
            return "Modif réussie";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur modif";
    }
    @GetMapping("/getEcheanceId")
    public static String getEcheanceId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("idecheance")) {
                    return cookie.getValue();
                }
            }
        }
        return "null"; // Cookie non trouvé
    }
    //fin echeance vehicule
}
