package fr.projet.enchere.projet_grp7_enchere.dal.articleDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;

import java.util.List;

public interface ArticleDAO {

    public void insert(Article article);

    public List<Article> getAll();

}
