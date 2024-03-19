package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleService;
import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleServiceException;
import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bll.enchereService.EnchereService;
import fr.projet.enchere.projet_grp7_enchere.bll.enchereService.EnchereServiceException;
import fr.projet.enchere.projet_grp7_enchere.bll.retraitService.RetraitService;
import fr.projet.enchere.projet_grp7_enchere.bll.retraitService.RetraitServiceException;
import fr.projet.enchere.projet_grp7_enchere.bll.storageService.StorageService;
import fr.projet.enchere.projet_grp7_enchere.bo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
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
    public String init(Article article, Retrait retrait) {
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
    public String valid(Article article, Retrait retrait,  BindingResult errors, @RequestParam("categorie") int noCategorie, @RequestParam("file") MultipartFile file, Model model) {

        HttpSession session = request.getSession(false);
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");

        // Récupérer la catégorie sélectionnée depuis la requête HTTP
        Categorie categorie = categorieService.getCategorieById(noCategorie);

        if (errors.hasErrors()) {
            return "article";
        }

        try {

            if (!file.isEmpty()) {
                // Récupérez le nom du fichier
                String fileName = file.getOriginalFilename();
                // Définissez le chemin de stockage pour l'image, par exemple dans le dossier "uploads" de votre application
                String uploadDir = "/uploads/";
                // Construisez le chemin complet du fichier
                String filePath = uploadDir + fileName;
                // Enregistrez le fichier sur le disque
                storageService.store(file);
                // Stockez le chemin de l'image dans l'article
                article.setArticle_img(filePath);
            }

            article.setUtilisateur(currentUser);
            article.setCategorie(categorie);


            // Si les dates d'enchères sont nulles, utilisez la date actuelle
            if (article.getDate_debut_encheres() == null) {
                article.setDate_debut_encheres(LocalDate.now());
            }
            if (article.getDate_fin_encheres() == null) {
                article.setDate_fin_encheres(LocalDate.now());
            }


            // Ajouter l'article à la base de données
            service.addArticle(article);
            retraitService.addRetrait(retrait);


            // Créer une instance d'enchère
            Enchere enchere = new Enchere();
            enchere.setUtilisateur(currentUser);
            enchere.setArticle(article);
            enchere.setRetrait(retrait);
            enchere.setMontant_enchere(article.getPrix_initial()); // Utilisez le prix initial de l'article comme montant initial de l'enchère
            enchere.setDate_enchere(article.getDate_debut_encheres()); // Utilisez la date de début d'enchère de l'article

            // Ajouter l'enchère à la base de données
            enchereService.addEnchere(enchere);



        } catch (ArticleServiceException e) {
            // Gérer l'exception
            e.printStackTrace();
        } catch (IOException | EnchereServiceException | RetraitServiceException e) {
            throw new RuntimeException(e);
        }

        // Rediriger vers la page d'affichage des articles
        return "redirect:/article";
    }

    @GetMapping("/detail")
    public String showArticleDetail(){
        return "view-article-detail";
    }


}
