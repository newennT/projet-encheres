package fr.projet.enchere.projet_grp7_enchere.dal.retraitDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Retrait;

import java.util.List;

public interface RetraitDAO {

    public void insert(Retrait retrait);

    public List<Retrait> getAll();
}
