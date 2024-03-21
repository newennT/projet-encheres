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
import org.thymeleaf.context.IContext;

/**
 * Controller class responsible for handling requests related to user details and profiles.
 */
@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

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
     * @param no    The no (id) of the user to be displayed.
     * @param model The model to add attributes for the view.
     * @return The view for displaying the details of the user.
     */
    @GetMapping("/details")
    public String afficherUnUtilisateur(@RequestParam(name = "no") long no, Model model) {
        Utilisateur utilisateur = utilisateurService.trouverParNo(no);

        if (utilisateur != null) {
            model.addAttribute("utilisateur", utilisateur);

            return "view-user-detail";
        }

        return "redirect:/";
    }

    /**
     * Handles GET requests for displaying the profile of a user.
     *
     * @param no    The no (id) of the user whose profile is to be displayed.
     * @param model The model to add attributes for the view.
     * @return The view for displaying the user's profile.
     */
    @GetMapping("/my-profil")
    public String afficherProfil(@RequestParam(name = "no") long no, Model model) {
        Utilisateur profil = utilisateurService.trouverParNo(no);

        if (profil != null) {
            model.addAttribute("profil", profil);

            return "view-profil";
        }

        return "redirect:/";
    }

    /**
     * Handles the POST request for modifying user profile.
     *
     * @param profil        The user profile data to be modified
     * @param bindingResult BindingResult object to hold validation errors
     * @return The view name to redirect to after the modification
     */
    @PostMapping("/my-profil")
    public String modificationProfil(@Valid @ModelAttribute("profil") Utilisateur profil, BindingResult bindingResult, Model model,
                                     @RequestParam(name = "nouveauMotDePasse", required = false)
                                     String nouveauMotDePasse,
                                     @RequestParam(name = "confirmationNouveauMotDePasse", required = false)
                                     String confirmationNouveauMotDePasse) {
        boolean mdpCheck = false;

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
                    bindingResult.rejectValue("pseudo", "Un utilisateur possède déjà ce pseudonyme.");
                    return "view-profil";
                }
            }

            // Check if the email already exists in the database
            Utilisateur similarEmail = utilisateurService.trouverParEmail(profil.getEmail());
            if (similarEmail != null) {
                if (currentUser.getNoUtilisateur() != similarEmail.getNoUtilisateur()) {
                    bindingResult.rejectValue("email", "Un utilisateur possède déjà cette adresse email.");
                    return "view-profil";
                }
            }

            // Checks if there is a new password (pattern + confirmation)
            if (nouveauMotDePasse != null && !nouveauMotDePasse.isEmpty() && confirmationNouveauMotDePasse != null
                    && !confirmationNouveauMotDePasse.isEmpty()) {
                if (!utilisateurService.isValidPassword(nouveauMotDePasse)) {
                    model.addAttribute("invalidNewPassword",
                            "Le mot de passe doit contenir au moins 8 caractères, dont au moins une lettre " +
                                    "minuscule, une lettre majuscule, un chiffre et un caractère spécial parmi @$!%*?&.");
                    return "view-profil";
                }

                if (!nouveauMotDePasse.equals(confirmationNouveauMotDePasse)) {
                    model.addAttribute("newPasswordConfirmation", "Les mots de passe ne correspondent pas.");
                    return "view-profil";
                }

                mdpCheck = true;
            }

            // Check if actual password is ok
            if (!utilisateurService.doPasswordsMatch(profil.getMotDePasse(), currentUser.getMotDePasse())) {
                model.addAttribute("motDePasseCheck", "Mot de passe incorrect.");
                return "view-profil";
            }

            // Set the new password if mdpCheck is valid
            if(mdpCheck){
                profil.setMotDePasse(nouveauMotDePasse);
            }

            profil.setNoUtilisateur(currentUser.getNoUtilisateur());
            utilisateurService.modifierUtilisateur(profil);

            return "redirect:/utilisateur/my-profil?no=" + currentUser.getNoUtilisateur();
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
