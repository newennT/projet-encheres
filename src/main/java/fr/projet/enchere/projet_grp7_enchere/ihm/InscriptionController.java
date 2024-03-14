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

    @Autowired
    UtilisateurService utilisateurService;

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
     * @param utilisateur    The user object submitted from the registration form.
     * @param bindingResult  BindingResult for validation errors.
     * @return Redirect to the index page if registration is successful, otherwise return to the registration page.
     */
    @PostMapping("/inscription")
    public String inscription(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {
        // Check if password confirmation matches
        if (!utilisateur.getMotDePasse().equals(utilisateur.getMotDePasseConfirmation())) {
            // Add error message if password confirmation does not match
            bindingResult.rejectValue("motDePasseConfirmation", "motDePasseConfirmation");
        }

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Return to the registration page if there are errors
            return "view-inscription";
        } else {
            // Add the user to the database if no validation errors are found
            utilisateurService.ajouterUtilisateur(utilisateur);
            // Redirect to the index page
            return "redirect:/view-index";
        }
    }
}
