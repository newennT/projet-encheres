package fr.projet.enchere.projet_grp7_enchere.ihm;

import fr.projet.enchere.projet_grp7_enchere.bll.CategorieService;
import fr.projet.enchere.projet_grp7_enchere.bll.CategorieServiceException;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    CategorieService service;

    @ModelAttribute("lstCategories")
    private List<Categorie> getListCategories(){
        return service.getAll();
    }

    @GetMapping
    public String init(Categorie categorie) {
        return "categories";
    }

    @PostMapping
    public String valid(Categorie categorie, BindingResult errors, Model model) {
        if(errors.hasErrors()) {
            return "categories";
        }

        try {
            service.addCategorie(categorie);
        } catch (CategorieServiceException e) {
            // TODO gérer l'exception
            e.printStackTrace();
        }
        return "redirect:/categories";
    }

}
