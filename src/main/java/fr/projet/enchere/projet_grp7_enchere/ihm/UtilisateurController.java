package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService){this.utilisateurService = utilisateurService;}


    boolean erreurMotDePasseNouveau = false;
    boolean erreurMotDePasseActuel = false;
    boolean erreurMotDePasseConfirmation = false;

    @GetMapping("/detail")
    public String afficherUnUtilisateur(@RequestParam(name = "pseudo", required = true) String pseudo, Model model) {
        if (pseudo != null) {
            System.out.println("Pseudo non null");
            Utilisateur utilisateur = utilisateurService.trouverParPseudo(pseudo);
            if (utilisateur != null) {
                System.out.println("Utilisateur bien récupéré par le service");
                model.addAttribute("utilisateur", utilisateur);
                return "view-user-detail";
            } else {System.out.println("Utilisateur inconnu !");}
        } else {System.out.println("Pseudo inconnu !");}
        return "redirect:/";
    }

    @GetMapping("/profil")
    public String afficherProfil(@RequestParam(name = "pseudo", required = true) String pseudo,
                                 Model model) {
        System.out.println(model);
        if (pseudo != null) {
            Utilisateur profil = utilisateurService.trouverParPseudo(pseudo);
            if (profil != null) {
                model.addAttribute("profil", profil);
                model.addAttribute("erreurMotDePasseNouveau", erreurMotDePasseNouveau);
                model.addAttribute("erreurMotDePasseActuel", erreurMotDePasseActuel);
                model.addAttribute("erreurMotDePasseConfirmation", erreurMotDePasseConfirmation);
                System.out.println(profil);
                return "view-profil";
            } else {System.out.println("Utilisateur inconnu !");}
        } else {System.out.println("Pseudo inconnu !");}
        return "redirect:/";
    }

    @PostMapping("/profil")
    public String modificationProfil(@Valid @ModelAttribute("profil") Utilisateur profil, BindingResult bindingResult,
                                     /*@ModelAttribute("erreurMotDePasseNouveau") boolean erreurMotDePasseNouveau,
                                     @ModelAttribute("erreurMotDePasseActuel") boolean erreurMotDePasseActuel,
                                     @ModelAttribute("erreurMotDePasseConfirmation") boolean erreurMotDePasseConfirmation,*/
                                     @RequestParam(name = "motDePasseNouveau", required = false) String motDePasseNouveau,
                                     @RequestParam(name = "motDePasseActuel", required = true) String motDePasseActuel,
                                     @RequestParam(name = "motDePasseConfirmation", required = false) String motDePasseConfirmation) {
        System.out.println("Début Post");
        System.out.println(profil);
        System.out.println(erreurMotDePasseNouveau);
        System.out.println(erreurMotDePasseActuel);
        System.out.println(erreurMotDePasseConfirmation);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            System.out.println("BindingResult error");
            return "view-profil";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Utilisateur currentUser = utilisateurService.trouverParPseudo(authentication.getName());
            System.out.println("Auth. get Name : " +authentication.getName());

            // Check if the pseudo already exists in the database
            Utilisateur similarPseudo = utilisateurService.trouverParPseudo(profil.getPseudo());
            if (similarPseudo != null) {
                if (currentUser.getNoUtilisateur() != similarPseudo.getNoUtilisateur()) {
                    System.out.println("Déjà pseudo");
                    bindingResult.rejectValue("pseudo", "similarPseudonyme");
                    return "view-profil";
                }
            }

            // Check if the email already exists in the database
            Utilisateur similarEmail = utilisateurService.trouverParEmail(profil.getEmail());
            if (similarEmail != null ) {
                if (currentUser.getNoUtilisateur() != similarEmail.getNoUtilisateur()) {
                    System.out.println("Current : " + currentUser.getNoUtilisateur());
                    System.out.println("Similar : " + similarEmail.getNoUtilisateur());
                    System.out.println("Déjà mail");
                    bindingResult.rejectValue("email", "similarEmail");
                    return "view-profil";
                }
            }

            // Check if actual password is ok
            //if (!currentUser.getMotDePasse().equals(profil.getMotDePasseActuel())) {
            if (!utilisateurService.doPasswordsMatch(motDePasseActuel,currentUser.getMotDePasse())) {
                System.out.println("Pas le bon mot de passe");
                erreurMotDePasseActuel = true;
                return "view-profil";
            }

            // Checks if there is a new password (pattern + confirmation)
            if (motDePasseNouveau != null) {
                System.out.println("Nouveau mot de passe rempli");

                //Check if the new password matches with the pattern
                if (!utilisateurService.isValidPassword(motDePasseNouveau)) {
                    erreurMotDePasseNouveau = true;
                    return "view-profil";
                }

                //Check if the confirmation matches with the new password
                if (!motDePasseConfirmation.equals(motDePasseNouveau)){
                    System.out.println("Pas bonne confirmation mot de passe");
                    erreurMotDePasseConfirmation = true;
                    return "view-profil";
                } else {
                    System.out.println("Mot de passe = nouveau mot de passe");
                    profil.setMotDePasse(motDePasseNouveau);
                }
            }
            System.out.println("Passage au service");
            System.out.println(profil);
            utilisateurService.modifierUtilisateur(profil);

            return "redirect:/view-index";
        }
    }

    /**
     * Handles the request to delete the user profil.
     *
     * @param pseudo The username of the current user.
     * @return Redirection to the home page.
     */
    @GetMapping("/delete-profil")
    public String supprimerProfil(@RequestParam(name = "pseudo", required = true) String pseudo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = utilisateurService.trouverParPseudo(authentication.getName());
        if (currentUser != null) {
            utilisateurService.supprimerUtilisateur(currentUser);
            return "redirect:/logout";
        }
        return "redirect:/";
    }

}
