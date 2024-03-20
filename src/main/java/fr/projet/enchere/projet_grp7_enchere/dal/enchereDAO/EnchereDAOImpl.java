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

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("no_article", enchere.getArticle().getNo_article());
        namedParameters.addValue("no_utilisateur", enchere.getUtilisateur().getNoUtilisateur());
        namedParameters.addValue("date_enchere", enchere.getDate_enchere());
        namedParameters.addValue("montant_enchere", enchere.getMontant_enchere());

        jdbcTemplate.update("INSERT INTO ENCHERES (no_article, no_utilisateur, date_enchere, montant_enchere) " +
                "VALUES (:no_article, :no_utilisateur, :date_enchere, :montant_enchere)", namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            enchere.setNo_enchere(keyHolder.getKey().intValue());
        }
    }




    @Override
    public List<Enchere> getAll() {
        return jdbcTemplate.query("SELECT E.*, U.*, AV.* " +
                "FROM ENCHERES E " +
                "JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur " +
                "JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article", new EnchereRowMapper());
    }

    /**
     * Classe de mapping pour gérer l'association vers Enchere
     */
    static class EnchereRowMapper implements RowMapper<Enchere> {
        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enchere enchere = new Enchere();
            enchere.setNo_enchere(rs.getInt("no_enchere"));
            enchere.setDate_enchere(rs.getDate("date_enchere").toLocalDate());
            enchere.setMontant_enchere(rs.getInt("montant_enchere"));

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
            enchere.setUtilisateur(utilisateur);

            // Association vers l'article
            Article article = new Article();
            article.setNo_article(rs.getInt("no_article"));
            article.setNom_article(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
            article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
            article.setPrix_initial(rs.getInt("prix_initial"));
            article.setPrix_vente(rs.getInt("prix_vente"));
            enchere.setArticle(article);

            return enchere;
        }
    }


}
