package fr.projet.enchere.projet_grp7_enchere.dal.enchereDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.*;
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
public class EnchereDAOImpl implements EnchereDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public void insert(Enchere enchere) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        System.out.println("---------------------ID UTILISATEUR : " + enchere.getUtilisateur().getNoUtilisateur());
        System.out.println("---------------------ID ARTICLE : " + enchere.getArticle().getNo_article());
        System.out.println("---------------------ID RETRAIT : " + enchere.getRetrait().getNo_retrait());
        System.out.println("---------------------date_encheres : " + enchere.getDate_enchere());
        System.out.println("---------------------montant_enchere : " + enchere.getMontant_enchere());



        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("no_article", enchere.getArticle().getNo_article());
        namedParameters.addValue("no_utilisateur", enchere.getUtilisateur().getNoUtilisateur());
        namedParameters.addValue("no_retrait", enchere.getRetrait().getNo_retrait());



        namedParameters.addValue("date_encheres", enchere.getDate_enchere());
        namedParameters.addValue("montant_enchere", enchere.getMontant_enchere());




        jdbcTemplate.update("INSERT INTO ENCHERES (no_article, no_utilisateur, no_retrait, date_encheres, montant_enchere) " +
                "VALUES (:no_article, :no_utilisateur, :no_retrait,:date_encheres, :montant_enchere)", namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            enchere.setNo_enchere(keyHolder.getKey().intValue());
        }

    }

    @Override
    public List<Enchere> getAll() {
        return jdbcTemplate.query("SELECT no_article, no_utilisateur, no_retrait, date_encheres, montant_enchere FROM ENCHERES", new ArticleRowMapper());
    }

    /**
     * Classe de mapping pour gérer l'association vers Categorie
     */
    class ArticleRowMapper implements RowMapper<Enchere> {
        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enchere a = new Enchere();
            a.setNo_enchere(rs.getInt("no_enchere"));
           a.setDate_enchere(rs.getDate("date_encheres").toLocalDate());
           a.setMontant_enchere(rs.getInt("montant_enchere"));


            //Association vers l'article
            Article article = new Article();
            article.setNo_article(rs.getInt("no_article"));
            a.setArticle(article);


            //Association vers le retrait
            Retrait retrait = new Retrait();
            retrait.setNo_retrait(rs.getInt("no_retrait"));
            a.setRetrait(retrait);

            return a;

        }
    }
}
