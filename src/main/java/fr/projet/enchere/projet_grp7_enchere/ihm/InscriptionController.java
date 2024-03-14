package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class for handling user registration.
 */
@Controller
public class InscriptionController {
    private final UtilisateurService utilisateurService;

    @Autowired
    public InscriptionController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Handles GET requests for the registration page.
     *
     * @param model Model object to add attributes for the view.
     * @return The name of the registration view.
     */
    @GetMapping("/inscription")
    public String inscription(Model model) {
        // Add an empty user object to the model to bind with the registration form
        model.addAttribute("utilisateur", new Utilisateur());
        return "view-inscription";
    }

    /**
     * Handles POST requests for user registration.
     *
     * @param utilisateur   The user object submitted from the registration form.
     * @param bindingResult BindingResult for validation errors.
     * @return Redirect to the index page if registration is successful, otherwise return to the registration page.
     */
    @PostMapping("/inscription")
    public String inscription(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "view-inscription";
        } else {
            // Check if the pseudo already exists in the database
            Utilisateur similarUtilisateur = utilisateurService.trouverParPseudo(utilisateur.getPseudo());
            if (similarUtilisateur != null) {
                bindingResult.rejectValue("pseudo", "similarPseudonyme");
                return "view-inscription";
            }

            // Check if the email already exists in the database
            Utilisateur similarEmail = utilisateurService.trouverParEmail(utilisateur.getEmail());
            if (similarEmail != null) {
                bindingResult.rejectValue("email", "similarEmail");
                return "view-inscription";
            }

            // Check if password confirmation matches
            if (!utilisateur.getMotDePasse().equals(utilisateur.getMotDePasseConfirmation())) {
                bindingResult.rejectValue("motDePasseConfirmation", "motDePasseConfirmation");
                return "view-inscription";
            }

            utilisateur.setCredit(100);
            utilisateurService.ajouterUtilisateur(utilisateur);

            return "redirect:/view-index";
        }
    }

}
