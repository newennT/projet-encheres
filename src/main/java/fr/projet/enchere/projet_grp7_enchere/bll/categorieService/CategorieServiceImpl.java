package fr.projet.enchere.projet_grp7_enchere.bll.categorieService;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.dal.categorieDAO.CategorieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the CategorieService interface.
 * Provides methods to manage categories.
 */
@Service
public class CategorieServiceImpl implements CategorieService {

    private final CategorieDAO dao;

    /**
     * Constructor injection to set the CategorieDAO dependency.
     *
     * @param dao The CategorieDAO implementation.
     */
    @Autowired
    public CategorieServiceImpl(CategorieDAO dao) {
        this.dao = dao;
    }

    /**
     * Retrieves a list of all categories.
     *
     * @return A list containing all categories.
     */
    @Override
    public List<Categorie> getAll() {
        return dao.getAll();
    }

    /**
     * Adds a new category.
     *
     * @param categorie The category to add.
     * @throws CategorieServiceException If an error occurs while adding the category.
     */
    @Override
    public void addCategorie(Categorie categorie) throws CategorieServiceException {
        dao.insert(categorie);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The category with the specified ID.
     */
    @Override
    public Categorie getCategorieById(int id) {
        return dao.getById(id);
    }
}
