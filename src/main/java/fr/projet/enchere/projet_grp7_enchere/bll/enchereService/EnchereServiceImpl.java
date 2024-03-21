package fr.projet.enchere.projet_grp7_enchere.bll.enchereService;

import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;
import fr.projet.enchere.projet_grp7_enchere.dal.enchereDAO.EnchereDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the EnchereService interface.
 * This class provides functionalities specified in the EnchereService interface using an EnchereDAO object to access data.
 */
@Service
public class EnchereServiceImpl implements EnchereService {

    private final EnchereDAO dao;

    /**
     * Constructor for the EnchereServiceImpl class.
     *
     * @param dao EnchereDAO object used to access auction data.
     */
    @Autowired
    public EnchereServiceImpl(EnchereDAO dao) {
        this.dao = dao;
    }

    /**
     * Adds an auction using the EnchereDAO object.
     *
     * @param enchere The auction to add.
     * @throws EnchereServiceException If an error occurs while adding the auction.
     */
    @Override
    @Transactional
    public void addEnchere(Enchere enchere) throws EnchereServiceException {
        dao.insert(enchere);
    }

    /**
     * Retrieves all auctions using the EnchereDAO object.
     *
     * @return A list containing all auctions.
     */
    @Override
    public List<Enchere> getAll() {
        return dao.getAll();
    }
}