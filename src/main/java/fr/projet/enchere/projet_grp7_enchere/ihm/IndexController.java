package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Controller handling requests related to the home page.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public IndexController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Initializes the home page and retrieves the list of users.
     *
     * @param model The model to pass data to the view.
     * @return The view to display for the home page.
     */
    @GetMapping
    public String init(Model model) {
        System.out.println("\nAll users: ");
        List<Utilisateur> utilisateurs = utilisateurService.getAll();
        model.addAttribute("utilisateurs", utilisateurs);

        return "view-index";
    }

    /**
     * Returns the home view.
     *
     * @return The view to display for the home page.
     */
    @GetMapping("/static")
    public String index() {
        return "view-index";
    }
}