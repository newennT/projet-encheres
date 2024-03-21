package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bll.categorieService.CategorieServiceException;
import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling user administration operations.
 */
@Controller
@RequestMapping("/administrator")
public class AdministrationController {

    private final UtilisateurService utilisateurService;
    private final CategorieService categorieService;

    /**
     * Constructor for AdministrationController.
     *
     * @param utilisateurService The service for managing users.
     */
    @Autowired
    public AdministrationController(UtilisateurService utilisateurService, CategorieService categorieService) {
        this.utilisateurService = utilisateurService;
        this.categorieService = categorieService;
    }

    /**
     * Retrieves a list of categories as a model attribute.
     *
     * @return The list of categories.
     */
    @ModelAttribute("lstCategories")
    private List<Categorie> getListCategories() {
        return categorieService.getAll();
    }

    /**
     * Handles the request to delete a user.
     *
     * @param no The no (id) of the user to delete.
     * @return Redirection to the home page.
     */
    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam(name = "no") long no) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = utilisateurService.trouverParPseudo(authentication.getName());

        if (currentUser.isAdministrateur()) {
            Utilisateur userToDelete = utilisateurService.trouverParNo(no);

            
            if (userToDelete != null) {
                utilisateurService.supprimerUtilisateur(userToDelete);
                return "redirect:/";
            }
        }

        return "redirect:/";
    }

    //TODO - Gestion des catégories -> Implémentation EN COURS
    /**
     * Handles the HTTP GET request to access the categories page.
     *
     * @return The name of the view associated with the categories page.
     */
    @GetMapping("/categories")
    public String viewCategories(Model model) {
        model.addAttribute("categorie", new Categorie());

        return "view-categories";
    }

    /**
     * Handles the HTTP POST request to add a new category.
     *
     * @param categorie     The category object to be added.
     * @param bindingResult The binding result containing potential validation errors.
     * @return The redirect URL after adding the category.
     */
    @PostMapping("/categories")
    public String valid(@Valid @ModelAttribute("categorie") Categorie categorie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-categories";
        }

        try {
            categorieService.addCategorie(categorie);
        } catch (CategorieServiceException e) {
            // TODO gérer l'exception
            e.printStackTrace();
        }

        return "redirect:/administrator/categories";
    }
}