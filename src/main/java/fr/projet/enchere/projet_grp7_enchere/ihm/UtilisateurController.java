package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService){this.utilisateurService = utilisateurService;}


    @GetMapping("/detail")
    public String afficherUnUtilisateur(@RequestParam(name = "pseudo", required = true) String pseudo, Model model) {
        if (pseudo != null) {
            Utilisateur utilisateur = utilisateurService.trouverParPseudo(pseudo);
            if (utilisateur != null) {
                model.addAttribute("utilisateur", utilisateur);
                return "layouts/view-user-detail";
            } else {System.out.println("Utilisateur inconnu !");}
        } else {System.out.println("Pseudo inconnu !");}
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String afficherProfil(@RequestParam(name = "pseudo", required = true) String pseudo, Model model) {
        if (pseudo != null) {
            Utilisateur profil = utilisateurService.trouverParPseudo(pseudo);
            if (profil != null) {
                model.addAttribute("profil", profil);
                return "layouts/view-profil";
            } else {System.out.println("Utilisateur inconnu !");}
        } else {System.out.println("Pseudo inconnu !");}
        return "redirect:/";
    }



}
