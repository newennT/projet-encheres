package fr.projet.enchere.projet_grp7_enchere.dal.enchereDAO;

import fr.projet.enchere.projet_grp7_enchere.bll.articleService.ArticleService;
import fr.projet.enchere.projet_grp7_enchere.bll.retraitService.RetraitService;
import fr.projet.enchere.projet_grp7_enchere.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    final String SELECT_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere=:id";

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


        jdbcTemplate.update("INSERT INTO ENCHERES (no_article, no_utilisateur, no_retrait, date_enchere, montant_enchere) " +
                "VALUES (:no_article, :no_utilisateur, :no_retrait,:date_encheres, :montant_enchere)", namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            enchere.setNo_enchere(keyHolder.getKey().intValue());
        }

    }

    @Override
    public List<Enchere> getAll() {
        return jdbcTemplate.query("SELECT U.*, AV.*, R.*, E.*\n" +
                "FROM ENCHERES E\n" +
                "JOIN ARTICLES_VENDUS AV ON AV.no_article = E.no_article\n" +
                "LEFT JOIN UTILISATEURS U ON U.no_utilisateur = E.no_utilisateur\n" +
                "LEFT JOIN RETRAITS R ON E.no_retrait = R.no_retrait;", new ArticleRowMapper());
    }

    /**
     * Classe de mapping pour gérer l'association vers Categorie
     */
    static class ArticleRowMapper implements RowMapper<Enchere> {
        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enchere enchere = new Enchere();
            enchere.setNo_enchere(rs.getInt("no_enchere"));
            enchere.setDate_enchere(rs.getDate("date_enchere").toLocalDate());
            enchere.setMontant_enchere(rs.getInt("montant_enchere"));

            // Association vers l'article
            Article article = new Article();
            article.setNo_article(rs.getInt("no_article"));
            article.setNom_article(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
            article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
            article.setPrix_initial(rs.getInt("prix_initial"));
            article.setPrix_vente(rs.getInt("prix_vente"));

            // Association vers l'utilisateur
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

            // Association vers le retrait
            Retrait retrait = new Retrait();
            retrait.setNo_retrait(rs.getInt("no_retrait"));
            retrait.setRue(rs.getString("rue"));
            retrait.setCode_postal(rs.getString("code_postal"));
            retrait.setVille(rs.getString("ville"));

            enchere.setArticle(article);
            enchere.setUtilisateur(utilisateur);
            enchere.setRetrait(retrait);

            return enchere;
        }
    }
}