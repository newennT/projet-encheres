package fr.projet.enchere.projet_grp7_enchere.dal.articleDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.*;
import fr.projet.enchere.projet_grp7_enchere.dal.enchereDAO.EnchereDAOImpl;
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

/**
 * Implementation of the ArticleDAO interface responsible for handling database operations related to articles.
 */
@Repository
public class ArticleDAOImpl implements ArticleDAO {

    final String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=:id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Constructor for ArticleDAOImpl.
     *
     * @param jdbcTemplate The NamedParameterJdbcTemplate to be used for database operations.
     */
    @Autowired
    public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Inserts a new article into the database.
     *
     * @param article The article to be inserted.
     */
    @Override
    public void insert(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("nom_article", article.getNom_article());
        namedParameters.addValue("description", article.getDescription());
        namedParameters.addValue("prix_initial", article.getPrix_initial());
        namedParameters.addValue("date_debut_encheres", article.getDate_debut_encheres());
        namedParameters.addValue("date_fin_encheres", article.getDate_fin_encheres());
        namedParameters.addValue("no_utilisateur", article.getUtilisateur().getNoUtilisateur());
        namedParameters.addValue("no_categorie", article.getCategorie().getNo_categorie());
        namedParameters.addValue("no_retrait", article.getRetrait().getNo_retrait());

        String sql = "INSERT INTO ARTICLES_VENDUS (nom_article, description, prix_initial, date_debut_encheres, date_fin_encheres, no_utilisateur, no_categorie, no_retrait) " +
                "VALUES (:nom_article, :description, :prix_initial, :date_debut_encheres, :date_fin_encheres, :no_utilisateur, :no_categorie, :no_retrait)";

        jdbcTemplate.update(sql, namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            article.setNo_article(keyHolder.getKey().intValue());
        }
    }


    /**
     * Retrieves a list of all articles from the database.
     *
     * @return A list containing all articles.
     */
    @Override
    public List<Article> getAll() {
        return jdbcTemplate.query("SELECT AV.no_article, AV.nom_article, AV.description, AV.date_debut_encheres, " +
                "AV.date_fin_encheres, AV.prix_initial, AV.prix_vente, AV.article_img, AV.no_retrait, " +
                "U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, " +
                "U.rue, U.code_postal, U.ville, U.mot_de_passe, U.credit, U.administrateur, " +
                "C.no_categorie, C.libelle, " +
                "R.no_retrait, R.rue as retrait_rue, R.code_postal as retrait_code_postal, R.ville as retrait_ville " +
                "FROM ARTICLES_VENDUS AV " +
                "LEFT JOIN RETRAITS R ON AV.no_retrait = R.no_retrait " +
                "INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur " +
                "INNER JOIN CATEGORIES C ON AV.no_categorie = C.no_categorie", new ArticleRowMapper());
    }

    /**
     * RowMapper class to handle mapping for the association with Article.
     */
    static class ArticleRowMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();

            article.setNo_article(rs.getInt("no_article"));
            article.setNom_article(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setArticle_img(rs.getString("article_img"));
            article.setPrix_initial(rs.getInt("prix_initial"));
            article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
            article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());

            //Association vers l'utilisateur
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
            utilisateur.setPseudo(rs.getString("pseudo"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setTelephone(rs.getString("telephone"));
            utilisateur.setRue(rs.getString("rue"));
            utilisateur.setCodePostal(rs.getString("code_postal"));
            utilisateur.setVille(rs.getString("ville"));
            utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
            utilisateur.setCredit(rs.getInt("credit"));
            utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
            article.setUtilisateur(utilisateur);

            //Association vers la catégorie
            Categorie categorie = new Categorie();
            categorie.setNo_categorie(rs.getInt("no_categorie"));
            categorie.setLibelle(rs.getString("libelle"));

            // Association vers le retrait
            Retrait retrait = new Retrait();
            retrait.setNo_retrait(rs.getInt("no_retrait"));
            retrait.setRue(rs.getString("retrait_rue"));
            retrait.setCode_postal(rs.getString("retrait_code_postal"));
            retrait.setVille(rs.getString("retrait_ville"));

            article.setCategorie(categorie);
            article.setRetrait(retrait);

            return article;
        }
    }
}





