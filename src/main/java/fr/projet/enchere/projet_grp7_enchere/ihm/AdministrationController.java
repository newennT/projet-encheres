package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller handling user administration operations.
 */
@Controller
public class AdministrationController {

    private final UtilisateurService utilisateurService;

    /**
     * Constructor for AdministrationController.
     *
     * @param utilisateurService The service for managing users.
     */
    @Autowired
    public AdministrationController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Handles the request to delete a user.
     *
     * @param pseudo The username of the user to delete.
     * @return Redirection to the home page.
     */
    @GetMapping("/delete-user")
    public String afficherProfil(@RequestParam(name = "pseudo") String pseudo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = utilisateurService.trouverParPseudo(authentication.getName());

        if (currentUser.isAdministrateur()) {
            Utilisateur userToDelete = utilisateurService.trouverParPseudo(pseudo);
            if (userToDelete != null) {
                utilisateurService.supprimerUtilisateur(userToDelete);
                return "redirect:/";
            }
        }

        return "redirect:/";
    }
}