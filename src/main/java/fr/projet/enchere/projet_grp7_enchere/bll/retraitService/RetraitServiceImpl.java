package fr.projet.enchere.projet_grp7_enchere.bll.retraitService;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Retrait;
import fr.projet.enchere.projet_grp7_enchere.dal.articleDAO.ArticleDAO;
import fr.projet.enchere.projet_grp7_enchere.dal.retraitDAO.RetraitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RetraitServiceImpl implements RetraitService {

    @Autowired
    RetraitDAO dao;

    @Override
    @Transactional
    public void addRetrait(Retrait retrait) throws RetraitServiceException {
        dao.insert(retrait);
    }
}