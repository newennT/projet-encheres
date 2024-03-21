package fr.projet.enchere.projet_grp7_enchere.dal.categorieDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;

import java.util.List;

/**
 * Interface for performing CRUD operations related to categories in the database.
 */
public interface CategorieDAO {

    /**
     * Retrieves a list of all categories from the database.
     *
     * @return A list containing all categories.
     */
    List<Categorie> getAll();

    /**
     * Inserts a new category into the database.
     *
     * @param categorie The category to be inserted.
     */
    void insert(Categorie categorie);

    /**
     * Retrieves a category from the database based on its ID.
     *
     * @param id The ID of the category to be retrieved.
     * @return The category object matching the provided ID.
     */
    Categorie getById(int id);
}
