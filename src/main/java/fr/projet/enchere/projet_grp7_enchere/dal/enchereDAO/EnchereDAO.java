package fr.projet.enchere.projet_grp7_enchere.dal.enchereDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;

import java.util.List;

public interface EnchereDAO {

    public void insert(Enchere enchere);

    public List<Enchere> getAll();
}