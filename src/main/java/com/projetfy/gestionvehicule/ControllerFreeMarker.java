package com.projetfy.gestionvehicule;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class ControllerFreeMarker {

    @Autowired
    private Configuration freemarkerConfig;

    @GetMapping("/ajoutA")
    public String ajoutA(Model model) {
        try {
            Template template = freemarkerConfig.getTemplate("ajout.ftl");

            Map<String, Object> data = new HashMap<>();
            data.put("stateDeclarations", "const [numero, setNumero] = useState('');");
            data.put("effectDeclarations", "function handleSubmit(event) {event.preventDefault();console.log(marque);}"); 

            StringWriter writer = new StringWriter();
            template.process(data, writer);

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
