package fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService;

import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import fr.projet.enchere.projet_grp7_enchere.dal.utilisateurDAO.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the UtilisateurService interface providing business logic for user-related operations.
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurDAO utilisateurDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
        this.utilisateurDAO = utilisateurDAO;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Adds a new Utilisateur to the system.
     *
     * @param utilisateur The Utilisateur object to be added.
     */
    @Override
    @Transactional
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateurDAO.insert(utilisateur);
    }

    /**
     * Retrieves a Utilisateur record from the database based on the provided pseudo (username).
     *
     * @param pseudo The pseudo (username) of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided pseudo.
     */
    @Override
    public Utilisateur trouverParPseudo(String pseudo) {
        return utilisateurDAO.findByPseudo(pseudo);
    }

    /**
     * Retrieves a Utilisateur record from the database based on the provided email.
     *
     * @param email The email of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided email.
     */
    @Override
    public Utilisateur trouverParEmail(String email) {
        return utilisateurDAO.findByEmail(email);
    }

    @Override
    public List<Utilisateur> getAll() {
        return utilisateurDAO.getAll();
    }
}
