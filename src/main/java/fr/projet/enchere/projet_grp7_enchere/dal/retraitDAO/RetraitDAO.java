package fr.projet.enchere.projet_grp7_enchere.dal.retraitDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Retrait;

import java.util.List;

/**
 * Data Access Object (DAO) interface for Retrait entities.
 * This interface defines methods to interact with the database for Retrait entities.
 */
public interface RetraitDAO {

    /**
     * Inserts a Retrait entity into the database.
     *
     * @param retrait The Retrait entity to insert.
     */
    void insert(Retrait retrait);

    /**
     * Retrieves all Retrait entities from the database.
     *
     * @return A list containing all Retrait entities.
     */
    List<Retrait> getAll();
}
