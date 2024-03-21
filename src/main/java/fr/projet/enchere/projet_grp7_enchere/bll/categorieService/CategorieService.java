package fr.projet.enchere.projet_grp7_enchere.bll.categorieService;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;

import java.util.List;

/**
 * Interface for managing categories within the system.
 */
public interface CategorieService {

    /**
     * Retrieves a list of all categories.
     *
     * @return A list containing all categories.
     */
    List<Categorie> getAll();

    /**
     * Adds a new category.
     *
     * @param categorie The category to add.
     * @throws CategorieServiceException If an error occurs while adding the category.
     */
    void addCategorie(Categorie categorie) throws CategorieServiceException;

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The category with the specified ID.
     */
    Categorie getCategorieById(int id);
}

