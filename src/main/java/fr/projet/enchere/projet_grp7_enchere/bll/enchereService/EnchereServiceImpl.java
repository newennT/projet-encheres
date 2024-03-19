package fr.projet.enchere.projet_grp7_enchere.bll.enchereService;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;
import fr.projet.enchere.projet_grp7_enchere.dal.articleDAO.ArticleDAO;
import fr.projet.enchere.projet_grp7_enchere.dal.enchereDAO.EnchereDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService {

    @Autowired
    EnchereDAO dao;

    @Override
    @Transactional
    public void addEnchere(Enchere enchere) throws EnchereServiceException {
        dao.insert(enchere);
    }

    public List<Enchere> getAll() {
        return dao.getAll();
    }
}