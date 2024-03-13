package fr.projet.enchere.projet_grp7_enchere.bll.articleService;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;

import java.util.List;

public interface ArticleService {

    /**
     * Ajoute un article
     */
    public void addArticle(Article article) throws ArticleServiceException;

    /**
     * Liste tous les articles
     */
    public List<Article> getAll();
}
