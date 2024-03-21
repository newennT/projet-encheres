package fr.projet.enchere.projet_grp7_enchere.dal.articleDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    // SQL REQUESTS
    final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, prix_initial, " +
            "date_debut_encheres, date_fin_encheres, no_utilisateur, no_categorie, no_retrait) " +
            "VALUES (:nom_article, :description, :prix_initial, :date_debut_encheres, :date_fin_encheres, " +
            ":no_utilisateur, :no_categorie, :no_retrait)";

    final String SELECT = "SELECT AV.no_article, AV.nom_article, AV.description, AV.date_debut_encheres, " +
            "AV.date_fin_encheres, AV.prix_initial, AV.prix_vente, AV.article_img, AV.no_retrait, " +
            "U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, " +
            "U.rue, U.code_postal, U.ville, U.mot_de_passe, U.credit, U.administrateur, " +
            "C.no_categorie, C.libelle, " +
            "R.no_retrait, R.rue as retrait_rue, R.code_postal as retrait_code_postal, R.ville as retrait_ville " +
            "FROM ARTICLES_VENDUS AV " +
            "LEFT JOIN RETRAITS R ON AV.no_retrait = R.no_retrait " +
            "INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN CATEGORIES C ON AV.no_categorie = C.no_categorie";

    final String SELECT_BY_ID = "SELECT AV.no_article, AV.nom_article, AV.description, AV.date_debut_encheres, " +
            "AV.date_fin_encheres, AV.prix_initial, AV.prix_vente, AV.article_img, AV.no_retrait, " +
            "U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, " +
            "U.rue, U.code_postal, U.ville, U.mot_de_passe, U.credit, U.administrateur, " +
            "C.no_categorie, C.libelle, " +
            "R.no_retrait, R.rue as retrait_rue, R.code_postal as retrait_code_postal, R.ville as retrait_ville " +
            "FROM ARTICLES_VENDUS AV " +
            "LEFT JOIN RETRAITS R ON AV.no_retrait = R.no_retrait " +
            "LEFT JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur " +
            "LEFT JOIN CATEGORIES C ON AV.no_categorie = C.no_categorie " +
            "WHERE no_article=:id";

    final String UPDATE_PRIX = "UPDATE ARTICLES_VENDUS SET prix_vente = :montant WHERE no_article = :no";

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
        namedParameters.addValue("prix_vente", article.getPrix_initial());
        namedParameters.addValue("date_debut_encheres", article.getDate_debut_encheres());
        namedParameters.addValue("date_fin_encheres", article.getDate_fin_encheres());
        namedParameters.addValue("no_utilisateur", article.getUtilisateur().getNoUtilisateur());
        namedParameters.addValue("no_categorie", article.getCategorie().getNo_categorie());
        namedParameters.addValue("no_retrait", article.getRetrait().getNo_retrait());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
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
        return jdbcTemplate.query(SELECT, new ArticleRowMapper());
    }

    /**
     * Retrieves an Enchere entity by its identifier from the database.
     *
     * @param id The identifier of the Enchere entity to retrieve.
     * @return The Enchere entity corresponding to the specified identifier.
     */
    @Override
    public Article getByID(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        List<Article> articles = jdbcTemplate.query(SELECT_BY_ID, namedParameters, new ArticleRowMapper());

        if (articles.isEmpty()) {
            return null;
        } else {
            return articles.get(0);
        }
    }

    /**
     * Updates the price in the database with the specified amount for the given item number.
     *
     * @param montant The amount to update the price by.
     * @param no The item number for which the price should be updated.
     */
    @Override
    public void updatePrix(int montant, long no) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("montant", montant);
        namedParameters.addValue("no", no);
        jdbcTemplate.update(UPDATE_PRIX, namedParameters);
    }

    /**
     * RowMapper class to handle mapping for the association with Article.
     */
    static class ArticleRowMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();

            // Mapping article data from the database
            article.setNo_article(rs.getInt("no_article"));
            article.setNom_article(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setArticle_img(rs.getString("article_img"));
            article.setPrix_initial(rs.getInt("prix_initial"));
            article.setPrix_vente(rs.getInt("prix_vente"));
            article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
            article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());

            // Association with the selling user of the article
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

            // Association with the category of the article
            Categorie categorie = new Categorie();
            categorie.setNo_categorie(rs.getInt("no_categorie"));
            categorie.setLibelle(rs.getString("libelle"));
            article.setCategorie(categorie);

            // Association with the withdrawal location of the article
            Retrait retrait = new Retrait();
            retrait.setNo_retrait(rs.getInt("no_retrait"));
            retrait.setRue(rs.getString("retrait_rue"));
            retrait.setCode_postal(rs.getString("retrait_code_postal"));
            retrait.setVille(rs.getString("retrait_ville"));
            article.setRetrait(retrait);

            return article;
        }
    }
}