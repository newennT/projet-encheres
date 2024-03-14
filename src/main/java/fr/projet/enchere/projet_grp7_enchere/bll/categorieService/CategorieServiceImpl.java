package fr.projet.enchere.projet_grp7_enchere.bll.categorieService;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.dal.categorieDAO.CategorieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {
    @Autowired
    CategorieDAO dao;

    @Override
    public List<Categorie> getAll() {
        return dao.getAll();
    }

    @Override
    public void addCategorie(Categorie categorie) throws CategorieServiceException {
        dao.insert(categorie);
    }


    @Override
    public Categorie getCategorieById(int id) {
        return dao.getById(id);
    }

}
