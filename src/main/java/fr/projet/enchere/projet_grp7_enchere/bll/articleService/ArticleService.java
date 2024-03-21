package fr.projet.enchere.projet_grp7_enchere.bll.articleService;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;

import java.util.List;

/**
 * This interface defines operations for managing articles within the system.
 */
public interface ArticleService {

    /**
     * Adds an article to the database.
     *
     * @param article The article to add.
     * @throws ArticleServiceException If an error occurs while adding the article.
     */
    void addArticle(Article article) throws ArticleServiceException;

    /**
     * Retrieves a list of all articles present in the database.
     *
     * @return A list containing all articles.
     */
    List<Article> getAll();

    /**
     * Retrieves an article by its identifier.
     *
     * @param id The identifier of the article to retrieve.
     * @return The article corresponding to the specified identifier.
     */
    Article getByID(long id);
}
