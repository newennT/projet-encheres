package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling user registration.
 */
@Controller
public class InscriptionController {
    private final UtilisateurService utilisateurService;

    /**
     * Constructs a new InscriptionController with the provided UtilisateurService.
     *
     * @param utilisateurService The UtilisateurService used for user-related operations.
     */
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
    public String inscription(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult,
                              @RequestParam("motDePasseConfirmation") String motDePasseConfirmation, Model model) {
        if (bindingResult.hasErrors()) {
            return "view-inscription";
        } else {
            Utilisateur similarUtilisateur = utilisateurService.trouverParPseudo(utilisateur.getPseudo());
            if (similarUtilisateur != null) {
                bindingResult.rejectValue("pseudo", "Un utilisateur possède déjà ce pseudonyme.");
                return "view-inscription";
            }

            Utilisateur similarEmail = utilisateurService.trouverParEmail(utilisateur.getEmail());
            if (similarEmail != null) {
                bindingResult.rejectValue("email", "Un utilisateur possède déjà cette adresse email.");
                return "view-inscription";
            }

            if (!utilisateur.getMotDePasse().equals(motDePasseConfirmation)) {
                model.addAttribute("motDePasseConfirmation", "Les mots de passe ne correspondent pas.");
                return "view-inscription";
            }

            utilisateur.setCredit(100);
            utilisateurService.ajouterUtilisateur(utilisateur);

            return "redirect:/";
        }
    }
}
