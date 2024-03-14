package fr.projet.enchere.projet_grp7_enchere.dal.categorieDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    public List<Categorie> getAll();

    public void insert(Categorie categorie);

    Categorie getById(int id);
}
