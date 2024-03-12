package fr.projet.enchere.projet_grp7_enchere.dal.utilisateurDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of the UtilisateurDAO interface for database operations related to Utilisateur entities.
 */
@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    final String SELECT = "SELECT * FROM UTILISATEURS";
    final String INSERT = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, " +
            "mot_de_passe, credit, administrateur) " +
            "VALUES(:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :credit, " +
            ":administrateur)";
    final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=:pseudo";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper<Utilisateur> mapper = (rs, i) -> new Utilisateur(rs.getInt("no_utilisateur"),
            rs.getString("pseudo"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("email"),
            rs.getString("telephone"),
            rs.getString("rue"),
            rs.getString("code_postal"),
            rs.getString("ville"),
            rs.getString("mot_de_passe"),
            rs.getInt("credit"),
            rs.getBoolean("administrateur"));

    /**
     * Retrieves all Utilisateur records from the database.
     *
     * @return A List of Utilisateur objects.
     */
    public List<Utilisateur> getAll() {
        return jdbcTemplate.query(SELECT, mapper);
    }

    /**
     * Inserts a new Utilisateur record into the database.
     *
     * @param utilisateur The Utilisateur object to be inserted.
     */
    public void insert(Utilisateur utilisateur) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(utilisateur);
        namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);
        utilisateur.setNoUtilisateur(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    /**
     * Retrieves a Utilisateur record from the database based on the provided pseudo (username).
     *
     * @param pseudo The pseudo (username) of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided pseudo.
     */
    public Utilisateur findByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", pseudo);
        return namedParameterJdbcTemplate.queryForObject(SELECT_BY_PSEUDO, namedParameters,
                new BeanPropertyRowMapper<>(Utilisateur.class));
    }
}
