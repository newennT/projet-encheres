package fr.projet.enchere.projet_grp7_enchere.dal.categorieDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class CategorieDAOImpl implements CategorieDAO{

    RowMapper<Categorie> rowMapper = (rs, i)->
            new Categorie(
                    rs.getInt("no_categorie"),
                    rs.getString("libelle")
            );


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void insert(Categorie categorie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("libelle", categorie.getLibelle());

        jdbcTemplate.update("INSERT INTO CATEGORIES (libelle) VALUES (:libelle)", namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            categorie.setNo_categorie(keyHolder.getKey().intValue());
        }

        // TODO : a supprimer
        System.out.println("insertion de "+categorie);
    }


    @Override
    public List<Categorie> getAll() {
        return jdbcTemplate.query("SELECT no_categorie, libelle FROM CATEGORIES", rowMapper);
    }

}
