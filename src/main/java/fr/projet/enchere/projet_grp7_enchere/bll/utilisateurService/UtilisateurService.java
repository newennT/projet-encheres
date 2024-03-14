package fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService;

import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;

import java.util.List;

/**
 * Interface defining the contract for the business logic layer operations related to Utilisateur (User) entities.
 */
public interface UtilisateurService {

    /**
     * Adds a new Utilisateur to the system.
     *
     * @param utilisateur The Utilisateur object to be added.
     */
    void ajouterUtilisateur(Utilisateur utilisateur);

    /**
     * Retrieves a Utilisateur record from the database based on the provided pseudo (username).
     *
     * @param pseudo The pseudo (username) of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided pseudo.
     */
    Utilisateur trouverParPseudo(String pseudo);

    /**
     * Retrieves a Utilisateur record from the database based on the provided email.
     *
     * @param email The email of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided email.
     */
    Utilisateur trouverParEmail(String email);

    List<Utilisateur> getAll();
}
