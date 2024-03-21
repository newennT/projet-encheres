package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//TODO - Gestion des utilisateur -> implémentation de la modification de son profil EN COURS

/**
 * Controller class responsible for handling requests related to user details and profiles.
 */
@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    //TODO - tests sur l'affichage ou non des erreurs liés aux changement de mot de passe
    // ou vérification du mot de passe actuel
    private boolean erreurMotDePasseNouveau = false;
    private boolean erreurMotDePasseActuel = false;
    private boolean erreurMotDePasseConfirmation = false;

    /**
     * Constructor for the UtilisateurController class.
     *
     * @param utilisateurService The service for handling user-related business logic.
     */
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Handles GET requests for displaying details of a specific user.
     *
     * @param pseudo The username (pseudo) of the user to be displayed.
     * @param model  The model to add attributes for the view.
     * @return The view for displaying the details of the user.
     */
    @GetMapping("/details")
    public String afficherUnUtilisateur(@RequestParam(name = "pseudo") String pseudo, Model model) {
        if (pseudo != null) {
            Utilisateur utilisateur = utilisateurService.trouverParPseudo(pseudo);
            if (utilisateur != null) {
                model.addAttribute("utilisateur", utilisateur);
                return "view-user-detail";
            }
        }

        return "redirect:/";
    }

    /**
     * Handles GET requests for displaying the profile of a user.
     *
     * @param pseudo The username (pseudo) of the user whose profile is to be displayed.
     * @param model  The model to add attributes for the view.
     * @return The view for displaying the user's profile.
     */
    @GetMapping("/my-profil")
    public String afficherProfil(@RequestParam(name = "pseudo") String pseudo, Model model) {
        if (pseudo != null) {
            Utilisateur profil = utilisateurService.trouverParPseudo(pseudo);
            if (profil != null) {
                model.addAttribute("profil", profil);
                model.addAttribute("erreurMotDePasseNouveau", erreurMotDePasseNouveau);
                model.addAttribute("erreurMotDePasseActuel", erreurMotDePasseActuel);
                model.addAttribute("erreurMotDePasseConfirmation", erreurMotDePasseConfirmation);

                return "view-profil";
            }
        }
        return "redirect:/";
    }

    //TODO - Edition du profil - EN COURS
    // bindingResults error alors qu'il n'est pas sensé y avoir d'erreur déclenchée

    /**
     * Handles the POST request for modifying user profile.
     *
     * @param profil                 The user profile data to be modified
     * @param bindingResult          BindingResult object to hold validation errors
     * @param motDePasseNouveau      New password input
     * @param motDePasseActuel       Current password input
     * @param motDePasseConfirmation Confirmation of the new password input
     * @return The view name to redirect to after the modification
     */
    @PostMapping("/my-profil")
    public String modificationProfil(@Valid @ModelAttribute("profil") Utilisateur profil, BindingResult bindingResult,
                                     @RequestParam(name = "motDePasseNouveau", required = false) String motDePasseNouveau,
                                     @RequestParam(name = "motDePasseActuel") String motDePasseActuel,
                                     @RequestParam(name = "motDePasseConfirmation", required = false) String motDePasseConfirmation) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "view-profil";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Utilisateur currentUser = utilisateurService.trouverParPseudo(authentication.getName());

            // Check if the pseudo already exists in the database
            Utilisateur similarPseudo = utilisateurService.trouverParPseudo(profil.getPseudo());
            if (similarPseudo != null) {
                if (currentUser.getNoUtilisateur() != similarPseudo.getNoUtilisateur()) {
                    bindingResult.rejectValue("pseudo", "similarPseudonyme");
                    return "view-profil";
                }
            }

            // Check if the email already exists in the database
            Utilisateur similarEmail = utilisateurService.trouverParEmail(profil.getEmail());
            if (similarEmail != null) {
                if (currentUser.getNoUtilisateur() != similarEmail.getNoUtilisateur()) {
                    bindingResult.rejectValue("email", "similarEmail");
                    return "view-profil";
                }
            }

            // Check if actual password is ok
            if (!utilisateurService.doPasswordsMatch(motDePasseActuel, currentUser.getMotDePasse())) {
                erreurMotDePasseActuel = true;
                return "view-profil";
            }

            // Checks if there is a new password (pattern + confirmation)
            if (motDePasseNouveau != null) {

                //Check if the new password matches with the pattern
                if (!utilisateurService.isValidPassword(motDePasseNouveau)) {
                    erreurMotDePasseNouveau = true;
                    return "view-profil";
                }

                //Check if the confirmation matches with the new password
                if (!motDePasseConfirmation.equals(motDePasseNouveau)) {
                    erreurMotDePasseConfirmation = true;
                    return "view-profil";
                } else {
                    profil.setMotDePasse(motDePasseNouveau);
                }
            }

            utilisateurService.modifierUtilisateur(profil);

            return "redirect:/utilisateur/my-profil";
        }
    }

    /**
     * Handles the request to delete the user profil.
     *
     * @return Redirection to the home page.
     */
    @GetMapping("/delete-my-account")
    public String supprimerProfil() {

        //Retrieve the currentUser pseudo for delete it
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = utilisateurService.trouverParPseudo(authentication.getName());

        if (currentUser != null) {
            utilisateurService.supprimerUtilisateur(currentUser);
            return "redirect:/logout";
        }
        return "redirect:/";
    }

}
