package fr.projet.enchere.projet_grp7_enchere.bll.retraitService;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Retrait;

import java.util.List;

public interface RetraitService {

    /**
     * Ajoute une date / instance de retrait
     */
    public void addRetrait(Retrait retrait) throws RetraitServiceException;

}
