package fr.projet.enchere.projet_grp7_enchere.dal.articleDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing Article entities.
 */
public interface ArticleDAO {

    /**
     * Inserts a new article into the database.
     *
     * @param article The article to be inserted.
     */
    void insert(Article article);

    /**
     * Retrieves a list of all articles from the database.
     *
     * @return A list containing all articles.
     */
    List<Article> getAll();

    /**
     * Retrieves an Article entity by its identifier from the data source.
     *
     * @param id The identifier of the Article entity to retrieve.
     * @return The Article entity corresponding to the specified identifier.
     */
    Article getByID(long id);

    /**
     * Updates the price in the database with the specified amount for the given item number.
     *
     * @param montant The amount to update the price by.
     * @param no The item number for which the price should be updated.
     */
    void updatePrix(int montant, long no);
}
