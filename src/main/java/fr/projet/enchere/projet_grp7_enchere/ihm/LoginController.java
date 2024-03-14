package fr.projet.enchere.projet_grp7_enchere.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for handling login-related requests.
 */
@Controller
public class LoginController {

    /**
     * Handles the GET request to "/login" endpoint, directing users to the login view.
     *
     * @return The logical view name for the login page.
     */
    @GetMapping("/login")
    public String authentification() {
        return "view-login";
    }

    /**
     * Handles the POST request to "/login-error" endpoint, displaying the login view with an error message.
     *
     * @param model The Spring MVC Model object to add attributes.
     * @return The logical view name for the login page with an error message.
     */
    @RequestMapping("/login-error")
    public String authenticationError(@RequestParam(name = "error", required = false) String error, Model model) {
        model.addAttribute("loginError", true);
        if ("invalid_credentials".equals(error)) {
            model.addAttribute("message", "incorrectLoginForm");
        } else {
            model.addAttribute("message", "incorrectLoginFormExcept");
        }
        return "view-login";
    }
}
