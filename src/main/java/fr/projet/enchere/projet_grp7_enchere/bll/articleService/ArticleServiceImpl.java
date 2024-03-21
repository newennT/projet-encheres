package fr.projet.enchere.projet_grp7_enchere.bll.articleService;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.dal.articleDAO.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the ArticleService interface.
 * Provides methods to add and retrieve articles.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO dao;

    /**
     * Constructor injection to set the ArticleDAO dependency.
     *
     * @param dao The ArticleDAO implementation.
     */
    @Autowired
    public ArticleServiceImpl(ArticleDAO dao) {
        this.dao = dao;
    }

    /**
     * Adds an article to the database.
     *
     * @param article The article to add.
     * @throws ArticleServiceException If an error occurs while adding the article.
     */
    @Override
    @Transactional
    public void addArticle(Article article) throws ArticleServiceException {
        dao.insert(article);
    }

    /**
     * Retrieves a list of all articles present in the database.
     *
     * @return A list containing all articles.
     */
    @Override
    public List<Article> getAll() {
        return dao.getAll();
    }

    /**
     * Retrieves an auction by its identifier using the EnchereDAO object.
     *
     * @param id The identifier of the auction to retrieve.
     * @return The auction corresponding to the specified identifier.
     */
    @Override
    public Article getByID(long id) {
        return dao.getByID(id);
    }

    /**
     * Updates the price in the database with the specified amount for the given item number.
     *
     * @param montant The amount to update the price by.
     * @param no The item number for which the price should be updated.
     */
    @Override
    public void updatePrix(int montant, long no) {
        dao.updatePrix(montant, no);
    }
}