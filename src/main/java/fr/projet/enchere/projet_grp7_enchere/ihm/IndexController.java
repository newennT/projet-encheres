package fr.projet.enchere.projet_grp7_enchere.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    /**
     * Initializes the home page and retrieves the list of articles.
     *
     * @return The view to display for the home page.
     */
    @GetMapping()
    public String init() {
        return "redirect:/encheres";
    }
}
