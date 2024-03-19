package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleService;
import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleServiceException;
import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService service;

    @Autowired
    CategorieService categorieService;


    @GetMapping
    public String init(Article article) {
        return "article";
    }

    @ModelAttribute("lstCategories")
    private List<Categorie> getListCategorie(){return categorieService.getAll();}

    @ModelAttribute("lstArticles")
    private List<Article> getListArticles(){
        return service.getAll();
    }

    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public String valid(Article article, BindingResult errors, Model model, @RequestParam("categorie") int noCategorie) {
        HttpSession session = request.getSession(false);

        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");

        // Récupérer la catégorie sélectionnée depuis la requête HTTP
        Categorie categorie = categorieService.getCategorieById(noCategorie);

        if(errors.hasErrors()) {
            return "article";
        }
        try {
            article.setUtilisateur(currentUser);
            article.setCategorie(categorie);


            if (article.getDate_debut_encheres() == null){
                article.setDate_debut_encheres(LocalDate.now());
            }

            if(article.getDate_fin_encheres() == null){
                article.setDate_fin_encheres(LocalDate.now());
            }
            service.addArticle(article);

        } catch (ArticleServiceException e) {
            // TODO gérer l'exception
            e.printStackTrace();
        }
        return "redirect:/article";
    }

    @GetMapping("/detail")
    public String showArticleDetail(){
        return "view-article-detail";
    }

}
