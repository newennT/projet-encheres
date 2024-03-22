package fr.projet.enchere.projet_grp7_enchere.bll.retraitService;

import fr.projet.enchere.projet_grp7_enchere.bo.Retrait;

/**
 * Interface representing a withdrawal service.
 */
public interface RetraitService {

    /**
     * Adds a withdrawal date/instance.
     *
     * @param retrait The withdrawal instance to add.
     */
    void addRetrait(Retrait retrait);
}
