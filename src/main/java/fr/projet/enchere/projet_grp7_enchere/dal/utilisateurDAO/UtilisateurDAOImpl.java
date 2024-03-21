package fr.projet.enchere.projet_grp7_enchere.dal.utilisateurDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    // SQL REQUESTS
    final String SELECT = "SELECT * FROM UTILISATEURS";

    final String INSERT = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, " +
            "mot_de_passe, credit, administrateur) " +
            "VALUES(:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :credit, " +
            ":administrateur)";
    final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=:pseudo";

    final String SELECT_BY_NO = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=:no";

    final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=:email";

    final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur=:no_utilisateur";

    final String UPDATE = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, " +
            "telephone = :telephone, rue = :rue, code_postal = :codePostal, ville = :ville, mot_de_passe = :motDePasse " +
            "WHERE no_utilisateur = :noUtilisateur";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Constructs a new UtilisateurDAOImpl object with the provided JdbcTemplate and NamedParameterJdbcTemplate.
     *
     * @param namedParameterJdbcTemplate The NamedParameterJdbcTemplate used for JDBC operations with named parameters.
     */
    @Autowired
    public UtilisateurDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // RowMapper to map ResultSet rows to Utilisateur objects
    RowMapper<Utilisateur> mapper = (rs, i) ->
            new Utilisateur(
                    rs.getInt("no_utilisateur"),
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
                    rs.getBoolean("administrateur")
            );

    /**
     * Retrieves all Utilisateur records from the database.
     *
     * @return A List of Utilisateur objects.
     */
    @Override
    public List<Utilisateur> getAll() {
        return namedParameterJdbcTemplate.query(SELECT, mapper);
    }

    /**
     * Inserts a new Utilisateur record into the database.
     *
     * @param utilisateur The Utilisateur object to be inserted.
     */
    @Override
    public void insert(Utilisateur utilisateur) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(utilisateur);
        namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
            utilisateur.setNoUtilisateur(Objects.requireNonNull(keyHolder.getKey()).intValue());
        }
    }

    /**
     * Retrieves a Utilisateur record from the database based on the provided pseudo (username).
     *
     * @param pseudo The pseudo (username) of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided pseudo.
     */
    @Override
    public Utilisateur findByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", pseudo);
        List<Utilisateur> utilisateurs = namedParameterJdbcTemplate.query(SELECT_BY_PSEUDO, namedParameters,
                new BeanPropertyRowMapper<>(Utilisateur.class));
        if (utilisateurs.isEmpty()) {
            return null;
        } else {
            return utilisateurs.get(0);
        }
    }

    /**
     * Retrieves a Utilisateur record from the database based on the provided no (id).
     *
     * @param no The no (id) of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided pseudo.
     */
    @Override
    public Utilisateur findByNo(long no) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no", no);
        List<Utilisateur> utilisateurs = namedParameterJdbcTemplate.query(SELECT_BY_NO, namedParameters,
                new BeanPropertyRowMapper<>(Utilisateur.class));
        if (utilisateurs.isEmpty()) {
            return null;
        } else {
            return utilisateurs.get(0);
        }
    }

    /**
     * Retrieves a Utilisateur record from the database based on the provided email.
     *
     * @param email The email of the Utilisateur to be retrieved.
     * @return The Utilisateur object matching the provided email.
     */
    @Override
    public Utilisateur findByEmail(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);
        List<Utilisateur> utilisateurs = namedParameterJdbcTemplate.query(SELECT_BY_EMAIL, namedParameters,
                new BeanPropertyRowMapper<>(Utilisateur.class));
        if (utilisateurs.isEmpty()) {
            return null;
        } else {
            return utilisateurs.get(0);
        }
    }

    /**
     * Removes a Utilisateur from the system.
     *
     * @param utilisateur The Utilisateur object to be removed.
     */
    @Override
    public void deleteUser(Utilisateur utilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur", utilisateur.getNoUtilisateur());
        namedParameterJdbcTemplate.update(DELETE_USER, namedParameters);
    }

    /**
     * Update the Utilisateur into the database.
     *
     * @param utilisateur The Utilisateur object to be modified.
     */
    @Override
    public void update(Utilisateur utilisateur) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(utilisateur);
        namedParameterJdbcTemplate.update(UPDATE, namedParameters);
    }
}
