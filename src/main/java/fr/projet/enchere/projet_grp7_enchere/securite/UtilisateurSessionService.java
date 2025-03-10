package fr.projet.enchere.projet_grp7_enchere.securite;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of Spring Security's UserDetailsService for loading user details during authentication.
 */
@Service
public class UtilisateurSessionService implements UserDetailsService {

    private final UtilisateurService utilisateurService;

    /**
     * Constructor for the UtilisateurSessionService class.
     *
     * @param utilisateurService The service for handling user-related business logic.
     */
    @Autowired
    public UtilisateurSessionService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Loads user details by username (pseudo) during authentication.
     *
     * @param pseudo The username (pseudo) of the user to load.
     * @return UserDetails object representing the authenticated user.
     * @throws UsernameNotFoundException If the user with the given username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String pseudo) {
        Utilisateur utilisateur;
        try {
            utilisateur = utilisateurService.trouverParPseudo(pseudo);
            if (utilisateur == null) {
                throw new UsernameNotFoundException(pseudo);
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException(pseudo);
        }

        return new UtilisateurSession(utilisateur);
    }

}
