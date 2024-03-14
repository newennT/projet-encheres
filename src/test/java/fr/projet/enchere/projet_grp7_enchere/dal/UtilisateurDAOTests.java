package fr.projet.enchere.projet_grp7_enchere.dal;

import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import fr.projet.enchere.projet_grp7_enchere.dal.utilisateurDAO.UtilisateurDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains JUnit tests for the UtilisateurDAO class.
 * It uses the SpringBootTest annotation to load the Spring application context.
 */
@SpringBootTest
public class UtilisateurDAOTests {

    @Autowired
    UtilisateurDAO utilisateurDAO;

    /**
     * Test method to ensure the proper functioning of UtilisateurDAO operations.
     * It creates two test Utilisateur objects, inserts them into the database, and retrieves them using DAO methods.
     */
    @Test
    @Transactional
    void contextLoads() {

    }

    @Test
    @Transactional
    void TestInsert() {
        // Creating test Utilisateur objects
        Utilisateur utilisateur1 = new Utilisateur("pseudo-test", "nom-test", "prenom-test",
                "email-test", "0781769982", "rue-test", "35200", "ville-test",
                "mdp-test", 0, false);
        utilisateurDAO.insert(utilisateur1);

        Utilisateur utilisateur2 = new Utilisateur("pseudo-test2", "nom-test2", "prenom-test2",
                "email-test2", "07817699822", "rue-test2", "352002", "ville-test2",
                "mdp-test2", 100, true);
        utilisateurDAO.insert(utilisateur2);

        // Retrieving Utilisateur by pseudo and printing it
        System.out.println(utilisateurDAO.findByPseudo("pseudo-test"));

        // Retrieving all Utilisateurs and printing them
        List<Utilisateur> utilisateurs = utilisateurDAO.getAll();
        utilisateurs.forEach(System.out::println);
    }

}

