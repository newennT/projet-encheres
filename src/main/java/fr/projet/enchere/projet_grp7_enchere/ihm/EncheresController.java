package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleService;
import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bll.enchereService.EnchereService;
import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import fr.projet.enchere.projet_grp7_enchere.securite.UtilisateurSession;
import fr.projet.enchere.projet_grp7_enchere.securite.UtilisateurSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Controller handling requests related to the home page.
 */
@Controller
@RequestMapping("/encheres")
public class EncheresController {

    private final CategorieService categorieService;
    private final ArticleService articleService;
    private final EnchereService enchereService;
    private final UtilisateurService utilisateurService;
    private final UtilisateurSessionService utilisateurSessionService;

    /**
     * Constructor for the IndexController class.
     * Initializes the controller with the required services.
     *
     * @param categorieService The service responsible for handling category-related operations.
     * @param articleService   The service responsible for handling article-related operations.
     */
    @Autowired
    public EncheresController(CategorieService categorieService, ArticleService articleService,
                              EnchereService enchereService, UtilisateurService utilisateurService,
                              UtilisateurSessionService utilisateurSessionService) {
        this.categorieService = categorieService;
        this.articleService = articleService;
        this.enchereService = enchereService;
        this.utilisateurService = utilisateurService;
        this.utilisateurSessionService = utilisateurSessionService;
    }

    /**
     * Initializes the home page and retrieves the list of articles.
     *
     * @return The view to display for the home page.
     */
    @GetMapping()
    public String init(Model model) {
        List<Categorie> categories = categorieService.getAll();
        model.addAttribute("lstCategories", categories);

        List<Article> articles = articleService.getAll();
        model.addAttribute("lstArticles", articles);

        return "view-index";
    }

    /**
     * Handles the HTTP GET request to access the auction details page.
     *
     * @param id    The identifier of the auction.
     * @param model The model to be populated with data for the view.
     * @return The name of the view associated with the auction details page.
     */
    @GetMapping("/details")
    public String afficherDetails(@RequestParam(name = "id") long id, Model model) {

        Article article = articleService.getByID(id);
        model.addAttribute("article", article);

        List<Enchere> encheres = enchereService.getAll();
        model.addAttribute("encheres", encheres);

        return "view-enchere-details";
    }

    /**
     * Handles the POST request for making a bid on an article.
     *
     * @param id The ID of the article.
     * @param propositon The proposed bid amount.
     * @param model The Spring model object for passing data to the view.
     * @return The view to redirect to after processing the bid.
     */
    @PostMapping("/details")
    public String faireUneProposition(@RequestParam(name = "id") long id,
                                      @RequestParam(name = "proposition") int propositon, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = utilisateurService.trouverParPseudo(authentication.getName());

        Article article = articleService.getByID(id);

        if (article.getPrix_vente() >= propositon){
            List<Enchere> encheres = enchereService.getAll();
            model.addAttribute("encheres", encheres);
            model.addAttribute("article", article);

            model.addAttribute("invalidProposition", "Votre proposition est trop basse.");

            return "view-enchere-details";
        }

        if(propositon > currentUser.getCredit()){
            List<Enchere> encheres = enchereService.getAll();
            model.addAttribute("encheres", encheres);
            model.addAttribute("article", article);

            model.addAttribute("invalidCredit", "Vous n'avez pas les fond nécessaires.");

            return "view-enchere-details";
        }

        currentUser.setCredit(currentUser.getCredit() - propositon);
        utilisateurService.updateCredit(currentUser.getCredit(), currentUser.getNoUtilisateur());

        articleService.updatePrix(propositon, id);

        Enchere enchere = new Enchere(article, currentUser, LocalDate.now(), propositon);
        enchereService.addEnchere(enchere);

        return "redirect:/encheres/details?id=" + id;
    }
}