package fr.projet.enchere.projet_grp7_enchere.bll.enchereService;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;

import java.util.List;

public interface EnchereService {

    /**
     * Ajoute une enchere
     */
    public void addEnchere(Enchere enchere) throws EnchereServiceException;

    /**
     * Liste toutes les encheres
     */
    public List<Enchere> getAll();
}
