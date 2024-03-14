package fr.projet.enchere.projet_grp7_enchere.bll;

import fr.projet.enchere.projet_grp7_enchere.bll.utilisateurService.UtilisateurService;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import fr.projet.enchere.projet_grp7_enchere.dal.utilisateurDAO.UtilisateurDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class contains JUnit tests for the UtilisateurService class.
 * It uses the SpringBootTest annotation to load the Spring application context.
 */
@SpringBootTest
public class UtilisateurServiceTests {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    UtilisateurDAO utilisateurDAO;

    /**
     * Test method to ensure the proper functioning of UtilisateurService operations.
     * It creates a test Utilisateur object, adds it to the system using the service, and retrieves it using DAO methods.
     */
    @Test
    @Transactional
    void contextLoads() {
        // Creating a test Utilisateur object
        Utilisateur utilisateur1 = new Utilisateur("pseudo-test", "nom-test", "prenom-test",
                "email-test", "0781769982", "rue-test", "35200", "ville-test",
                "mdp-test", 0, false);

        // Adding Utilisateur to the system using the service
        utilisateurService.ajouterUtilisateur(utilisateur1);

        // Retrieving Utilisateur by pseudo and printing it
        System.out.println(utilisateurService.trouverParPseudo("pseudo-test"));
    }

}

