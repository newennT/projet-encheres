package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String init(Model model) {
        System.out.println("\nTous les utilisateurs : ");
        List<Utilisateur> utilisateurs = utilisateurService.getAll();
        // Ajout des utilisateurs dans le modèle
        model.addAttribute("utilisateurs", utilisateurs);
        return "view-index";
    }

    @GetMapping("/static")
    public String index() {
        return "view-index";
    }
}
