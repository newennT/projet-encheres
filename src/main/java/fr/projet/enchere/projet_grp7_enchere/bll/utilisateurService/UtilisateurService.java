package fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService;

import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;

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
}
