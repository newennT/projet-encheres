package fr.projet.enchere.projet_grp7_enchere.bll.retraitService;

import fr.projet.enchere.projet_grp7_enchere.bo.Retrait;
import fr.projet.enchere.projet_grp7_enchere.dal.retraitDAO.RetraitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the RetraitService interface.
 * This class provides functionalities specified in the RetraitService interface using a RetraitDAO object to access data.
 */
@Service
public class RetraitServiceImpl implements RetraitService {

    private final RetraitDAO dao;

    /**
     * Constructor for the RetraitServiceImpl class.
     *
     * @param dao RetraitDAO object used to access withdrawal data.
     */
    @Autowired
    public RetraitServiceImpl(RetraitDAO dao) {
        this.dao = dao;
    }

    /**
     * Adds a withdrawal using the RetraitDAO object.
     *
     * @param retrait The withdrawal to add.
     * @throws RetraitServiceException If an error occurs while adding the withdrawal.
     */
    @Override
    @Transactional
    public void addRetrait(Retrait retrait) throws RetraitServiceException {
        dao.insert(retrait);
    }
}