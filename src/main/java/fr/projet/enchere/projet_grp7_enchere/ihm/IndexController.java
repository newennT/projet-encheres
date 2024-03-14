package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String init() {
        return "view-index";
    }

    @GetMapping("/static")
    public String index() {
        return "view-index";
    }
}
