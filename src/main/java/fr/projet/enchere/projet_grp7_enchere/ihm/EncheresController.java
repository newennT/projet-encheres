package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleService;
import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Controller handling requests related to the home page.
 */
@Controller
@RequestMapping("/encheres")
public class EncheresController {

    private final CategorieService categorieService;
    private final ArticleService articleService;

    /**
     * Constructor for the IndexController class.
     * Initializes the controller with the required services.
     *
     * @param categorieService The service responsible for handling category-related operations.
     * @param articleService   The service responsible for handling article-related operations.
     */
    @Autowired
    public EncheresController(CategorieService categorieService,
                              ArticleService articleService) {
        this.categorieService = categorieService;
        this.articleService = articleService;
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

        return "view-enchere-details";
    }
}