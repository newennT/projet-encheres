package fr.projet.enchere.projet_grp7_enchere.bll.enchereService;

import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;

import java.util.List;

/**
 * Interface representing an auction service.
 */
public interface EnchereService {

    /**
     * Adds an auction.
     *
     * @param enchere The auction to add.
     */
    void addEnchere(Enchere enchere);

    /**
     * Retrieves the list of all auctions.
     *
     * @return A list containing all auctions.
     */
    List<Enchere> getAll();
}
