package fr.projet.enchere.projet_grp7_enchere.dal.articleDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Article;
import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import fr.projet.enchere.projet_grp7_enchere.bo.Enchere;
import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
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

        System.out.println("---------------------ID UTILISATEUR : " + article.getUtilisateur().getNoUtilisateur());
        System.out.println("---------------------nom_article : " + article.getNom_article());
        System.out.println("---------------------description : " + article.getDescription());
        System.out.println("---------------------prix_initial : " + article.getPrix_initial());
        System.out.println("---------------------date_debut_encheres : " + article.getDate_debut_encheres());
        System.out.println("---------------------date_fin_encheres : " + article.getDate_fin_encheres());
        System.out.println("---------------------ID CATEGORIE : " + article.getCategorie().getNo_categorie());

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("categorie", article.getCategorie().getNo_categorie());
        namedParameters.addValue("no_utilisateur", article.getUtilisateur().getNoUtilisateur());
        namedParameters.addValue("nom_article", article.getNom_article());
        namedParameters.addValue("description", article.getDescription());
        namedParameters.addValue("article_img", article.getArticle_img());
        namedParameters.addValue("prix_initial", article.getPrix_initial());
        namedParameters.addValue("date_debut_encheres", article.getDate_debut_encheres());
        namedParameters.addValue("date_fin_encheres", article.getDate_fin_encheres());

        jdbcTemplate.update("INSERT INTO ARTICLES_VENDUS (" +
                "nom_article, " +
                "description, " +
                "prix_initial, " +
                "date_debut_encheres, " +
                "date_fin_encheres, " +
                "no_utilisateur, " +
                "no_categorie) " +
                "VALUES (" +
                ":nom_article, " +
                ":description, " +
                ":prix_initial, " +
                ":date_debut_encheres, " +
                ":date_fin_encheres, " +
                ":no_utilisateur, " +
                ":categorie)", namedParameters, keyHolder);


        jdbcTemplate.update("INSERT INTO ARTICLES_VENDUS (nom_article, description, article_img, prix_initial, date_debut_encheres, date_fin_encheres, no_utilisateur, no_categorie) " +
                "VALUES (:nom_article, :description, :article_img, :prix_initial, :date_debut_encheres, :date_fin_encheres, :no_utilisateur, :categorie)", namedParameters, keyHolder);

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
        return jdbcTemplate.query("SELECT AV.no_article, AV.nom_article, AV.description, AV.date_debut_encheres, \n" +
                "       AV.date_fin_encheres, AV.prix_initial, AV.prix_vente, AV.article_img, \n" +
                "       U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, \n" +
                "       U.rue, U.code_postal, U.ville, U.mot_de_passe, U.credit, U.administrateur,\n" +
                "       C.no_categorie, C.libelle\n" +
                "FROM ARTICLES_VENDUS AV\n" +
                "INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur\n" +
                "INNER JOIN CATEGORIES C ON AV.no_categorie = C.no_categorie;", new CategorieRowMapper());
    }

    /**
     * RowMapper class to handle mapping for the association with Categorie.
     */
    static class CategorieRowMapper implements RowMapper<Article> {
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
            article.setCategorie(categorie);

            return article;
        }
    }
}