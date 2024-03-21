package fr.projet.enchere.projet_grp7_enchere.dal.enchereDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;

import java.util.List;

/**
 * Data Access Object (DAO) interface for Enchere entities.
 */
public interface EnchereDAO {

    /**
     * Inserts an Enchere entity into the data source.
     *
     * @param enchere The Enchere entity to insert.
     */
    void insert(Enchere enchere);

    /**
     * Retrieves all Enchere entities from the data source.
     *
     * @return A list containing all Enchere entities.
     */
    List<Enchere> getAll();
}