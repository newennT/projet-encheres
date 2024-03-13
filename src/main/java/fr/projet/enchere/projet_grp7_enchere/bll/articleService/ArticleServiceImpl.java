package fr.projet.enchere.projet_grp7_enchere.bll.articleService;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.dal.articleDAO.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDAO dao;

    @Override
    @Transactional
    public void addArticle(Article article) throws ArticleServiceException {
        dao.insert(article);
    }

    public List<Article> getAll() {
        return dao.getAll();
    }
}