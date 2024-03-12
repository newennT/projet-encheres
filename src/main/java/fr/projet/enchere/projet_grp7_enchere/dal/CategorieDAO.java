package fr.projet.enchere.projet_grp7_enchere.dal;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    public List<Categorie> getAll();

    public void insert(Categorie categorie);
}
