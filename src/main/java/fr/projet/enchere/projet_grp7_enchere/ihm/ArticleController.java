package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleService;
import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bll.retraitService.RetraitService;
import fr.projet.enchere.projet_grp7_enchere.bo.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

//TODO - Ajout d'un article -> Implémentation EN COURS
/**
 * Controller class handling HTTP requests related to articles.
 */
@Controller
@RequestMapping("/encheres")
public class ArticleController {

    private final RetraitService retraitService;
    private final ArticleService service;
    private final CategorieService categorieService;

    /**
     * Constructs an ArticleController with the required services and dependencies.
     *
     * @param retraitService   The RetraitService instance.
     * @param service          The ArticleService instance.
     * @param categorieService The CategorieService instance.
     */
    @Autowired
    public ArticleController(RetraitService retraitService,
                             ArticleService service, CategorieService categorieService) {
        this.retraitService = retraitService;
        this.service = service;
        this.categorieService = categorieService;
    }

    /**
     * Handles the HTTP GET request to access the article page.
     *
     * @return The name of the view associated with the article page.
     */
    @GetMapping("/ajout-article")
    public String init(Model model) {
        model.addAttribute("article", new Article());

        List<Categorie> categories = categorieService.getAll();
        model.addAttribute("lstCategories", categories);

        return "view-article";
    }

    /**
     * Handles the HTTP POST request to add a new article.
     *
     * @param article The article object to be added.
     * @param bindingResult  The binding result containing potential validation errors.
     * @return The redirect URL after adding the article.
     */
    @PostMapping("/ajout-article")
    public String valid(@Valid @ModelAttribute("article") Article article, BindingResult bindingResult,
                        @RequestParam(name = "categorie") int categorieID, HttpSession session, Model model) {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");

        if (bindingResult.hasErrors()) {
            List<Categorie> categories = categorieService.getAll();
            model.addAttribute("lstCategories", categories);
            model.addAttribute("error", Objects.requireNonNull(bindingResult.getFieldError()).getField());
            return "view-article";
        }

        article.setUtilisateur(currentUser);

        Categorie categorie = categorieService.getCategorieById(categorieID);
        article.setCategorie(categorie);

        Retrait retrait = new Retrait(article.getRetrait().getRue(), article.getRetrait().getCode_postal(),
                article.getRetrait().getVille());
        retraitService.addRetrait(retrait);
        article.setRetrait(retrait);

        System.out.println(article.getUtilisateur().getNoUtilisateur());
        System.out.println(article.getCategorie().getNo_categorie());
        System.out.println(article.getRetrait().getNo_retrait());

        service.addArticle(article);

        return "redirect:/";
    }
}

