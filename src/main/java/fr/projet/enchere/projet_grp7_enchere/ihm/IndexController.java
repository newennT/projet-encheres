package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleService;
import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bll.enchereService.EnchereService;
import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;
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
    private final CategorieService categorieService;
    private final EnchereService enchereService;

    private final ArticleService articleService;

    public IndexController(UtilisateurService utilisateurService, CategorieService categorieService, EnchereService enchereService, ArticleService articleService) {
        this.utilisateurService = utilisateurService;
        this.categorieService = categorieService;
        this.enchereService = enchereService;
        this.articleService = articleService;
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

        List<Categorie> categories = categorieService.getAll();
        model.addAttribute("lstCategories", categories);

        List<Enchere> encheres = enchereService.getAll();
        model.addAttribute("lstEncheres", encheres);

        List<Article> articles = articleService.getAll();
        model.addAttribute("lstArticles", articles);


        return "view-index";
    }

}