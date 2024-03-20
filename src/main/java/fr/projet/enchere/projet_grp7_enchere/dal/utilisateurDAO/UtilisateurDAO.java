package fr.projet.enchere.projet_grp7_enchere.dal.utilisateurDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;

import java.util.List;

/**
 * Interface defining the contract for data access operations related to Utilisateur (User) entities.
 */
public interface UtilisateurDAO {

    /**
     * Retrieves all Utilisateur records from the database.
     *
     * @return A List of Utilisateur objects.
     */
    List<Utilisateur> getAll();

    /**
     * Inserts a new Utilisateur record into the database.
     *
     * @param utilisateur The Utilisateur object to be inserted.
     */
    void insert(Utilisateur utilisateur);

    /**
     * Retrieves a Utilisateur record from the database based on the provided pseudo (username).
     *
     * @param pseudo The pseudo (username) of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided pseudo.
     */
    Utilisateur findByPseudo(String pseudo);

    /**
     * Retrieves a Utilisateur record from the database based on the provided email.
     *
     * @param email The email of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided email.
     */
    Utilisateur findByEmail(String email);

    /**
     * Removes a Utilisateur from the system.
     *
     * @param utilisateur The Utilisateur object to be removed.
     */
    void deleteUser(Utilisateur utilisateur);
}