package fr.projet.enchere.projet_grp7_enchere.dal.articleDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
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
public class ArticleDAOImpl implements ArticleDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public void insert(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        System.out.println("---------------------ERREUR : " + article.getNo_utilisateur().getNoUtilisateur());


        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom_article", article.getNom_article());
        namedParameters.addValue("description", article.getDescription());
        namedParameters.addValue("prix_initial", article.getPrix_initial());
        namedParameters.addValue("date_debut_encheres", article.getDate_debut_encheres());
        namedParameters.addValue("date_fin_encheres", article.getDate_fin_encheres());

        namedParameters.addValue("no_utilisateur", article.getNo_utilisateur().getNoUtilisateur());
        namedParameters.addValue("no_categorie", article.getNo_categorie().getNo_categorie());


        jdbcTemplate.update("INSERT INTO ARTICLES_VENDUS (nom_article, description, prix_initial, date_debut_encheres, date_fin_encheres, no_utilisateur, no_categorie) " +
                "VALUES (:nom_article, :description, :prix_initial, :date_debut_encheres, :date_fin_encheres, :no_utilisateur, :no_categorie)", namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            article.setNo_article(keyHolder.getKey().intValue());
        }



        // TODO : a supprimer
        System.out.println("insertion de " + article);
    }

    @Override
    public List<Article> getAll() {
        return jdbcTemplate.query("SELECT no_article, nom_article, description, prix_initial, date_debut_encheres, date_fin_encheres, no_utilisateur, no_categorie FROM ARTICLES_VENDUS", new CategorieRowMapper());
    }

    /**
     * Classe de mapping pour gérer l'association vers Categorie
     */
    class CategorieRowMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article a = new Article();
            a.setNo_article(rs.getInt("no_article"));
            a.setNom_article(rs.getString("nom_article"));
            a.setDescription(rs.getString("description"));
            a.setPrix_initial(rs.getInt("prix_initial"));
            a.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
            a.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());


            //Association vers la catégorie
            Categorie categorie = new Categorie();
            categorie.setNo_categorie(rs.getInt("no_categorie"));
            a.setNo_categorie(categorie);
            return a;

        }
    }
}
