package fr.projet.enchere.projet_grp7_enchere.bll;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;

import java.util.List;

public interface CategorieService {

    /**
     * Liste toutes les catégories
     */
    public List<Categorie> getAll();

    /**
     * Ajoute une catégorie
     */
    public void addCategorie(Categorie categorie) throws CategorieServiceException;
}

