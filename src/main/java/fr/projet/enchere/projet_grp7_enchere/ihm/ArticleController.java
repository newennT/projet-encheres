package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleService;
import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleServiceException;
import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bll.enchereService.EnchereService;
import fr.projet.enchere.projet_grp7_enchere.bll.retraitService.RetraitService;
import fr.projet.enchere.projet_grp7_enchere.bll.retraitService.RetraitServiceException;
import fr.projet.enchere.projet_grp7_enchere.bll.storageService.StorageService;
import fr.projet.enchere.projet_grp7_enchere.bo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService service;

    @Autowired
    CategorieService categorieService;

    @Autowired
    StorageService storageService;

    @Autowired
    RetraitService retraitService;

    @Autowired
    EnchereService enchereService;


    @GetMapping
    public String init(Model model) {
        model.addAttribute("article", new Article());
        return "article";
    }

    @ModelAttribute("lstCategories")
    private List<Categorie> getListCategorie() {
        return categorieService.getAll();
    }

    @ModelAttribute("lstArticles")
    private List<Article> getListArticles() {
        return service.getAll();
    }

    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public String valid(@Valid @ModelAttribute("article") Article article, BindingResult errors, HttpSession session, Model model) throws RetraitServiceException, ArticleServiceException {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
        if (errors.hasErrors()) {
            return "article";
        }
        article.setUtilisateur(currentUser);

        Retrait retrait = new Retrait(article.getRetrait().getRue(), article.getRetrait().getCode_postal(), article.getRetrait().getVille());


        retraitService.addRetrait(retrait);
        service.addArticle(article);

        return "redirect:/";

    }

}

