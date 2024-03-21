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
     * Retrieves a Utilisateur record from the database based on the provided no (id).
     *
     * @param no The no (id) of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided pseudo.
     */
    Utilisateur trouverParNo(long no);

    /**
     * Retrieves a Utilisateur record from the database based on the provided email.
     *
     * @param email The email of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided email.
     */
    Utilisateur trouverParEmail(String email);

    /**
     * Removes a Utilisateur from the system.
     *
     * @param utilisateur The Utilisateur object to be removed.
     */
    void supprimerUtilisateur(Utilisateur utilisateur);

    /**
     * Retrieves all Utilisateurs from the system.
     *
     * @return List of all Utilisateur objects.
     */
    List<Utilisateur> getAll();

    /**
     * Modifies an Utilisateur in the Database.
     *
     * @param utilisateur The Utilisateur object to be modified.
     */
    void modifierUtilisateur(Utilisateur utilisateur);

    /**
     * Checks whether the provided password matches the encoded password.
     *
     * @param saisie          The input password.
     * @param encodedPassword The encoded password stored in the database.
     * @return True if the provided password matches the encoded password, false otherwise.
     */
    Boolean doPasswordsMatch(String saisie, String encodedPassword);

    /**
     * To validate the pattern of the new password in the profile edition
     *
     * @param password the new password
     * @return Yes if password is ok, no if not
     */
    Boolean isValidPassword(String password);

    /**
     * Updates the credit for the user with the specified ID.
     *
     * @param credit The new credit amount.
     * @param no The ID of the user whose credit is to be updated.
     */
    void updateCredit(int credit, long no);
}
