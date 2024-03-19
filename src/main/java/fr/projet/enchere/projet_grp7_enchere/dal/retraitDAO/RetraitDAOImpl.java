
package fr.projet.enchere.projet_grp7_enchere.dal.retraitDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.bo.Retrait;
import fr.projet.enchere.projet_grp7_enchere.dal.articleDAO.ArticleDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RetraitDAOImpl implements RetraitDAO {

    RowMapper<Retrait> rowMapper = (rs, i)->
            new Retrait(
                    rs.getInt("IdRetrait"),
                    rs.getString("rue"),
                    rs.getString("code_postal"),
                    rs.getString("ville")
            );

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public void insert(Retrait retrait) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        System.out.println("---------------------ID RETRAIT : " + retrait.getNo_retrait());
        System.out.println("---------------------rue : " + retrait.getRue());
        System.out.println("---------------------code_postal : " + retrait.getCode_postal());
        System.out.println("---------------------ville : " + retrait.getVille());



        MapSqlParameterSource namedParameters = new MapSqlParameterSource();



        namedParameters.addValue("rue", retrait.getRue());
        namedParameters.addValue("code_postal", retrait.getCode_postal());
        namedParameters.addValue("ville", retrait.getVille());


        jdbcTemplate.update("INSERT INTO RETRAITS (rue, code_postal, ville) " +
                "VALUES (:rue, :code_postal, :ville)", namedParameters, keyHolder);


        if (keyHolder != null && keyHolder.getKey() != null) {
            retrait.setNo_retrait(keyHolder.getKey().intValue());
        }

    }

    @Override
    public List<Retrait> getAll() {
        return jdbcTemplate.query("SELECT rue, code_postal, ville FROM RETRAITS", rowMapper);
    }

}

