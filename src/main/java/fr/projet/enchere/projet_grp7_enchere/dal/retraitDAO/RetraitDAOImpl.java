
package fr.projet.enchere.projet_grp7_enchere.dal.retraitDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Data Access Object (DAO) implementation for Retrait entities.
 * This class provides methods to interact with the database for Retrait entities.
 */
@Repository
public class RetraitDAOImpl implements RetraitDAO {

    // SQL REQUESTS
    final String INSERT = "INSERT INTO RETRAITS (rue, code_postal, ville) VALUES (:rue, :code_postal, :ville)";

    final String SELECT = "SELECT rue, code_postal, ville FROM RETRAITS";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Constructs a RetraitDAOImpl with a NamedParameterJdbcTemplate instance.
     *
     * @param jdbcTemplate The NamedParameterJdbcTemplate instance.
     */
    @Autowired
    public RetraitDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map ResultSet rows to Retratit objects
    RowMapper<Retrait> rowMapper = (rs, i) ->
            new Retrait(
                    rs.getInt("no_retrait"),
                    rs.getString("rue"),
                    rs.getString("code_postal"),
                    rs.getString("ville")
            );

    /**
     * Inserts a Retrait entity into the database.
     *
     * @param retrait The Retrait entity to insert.
     */
    @Override
    public void insert(Retrait retrait) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(retrait);
        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
            retrait.setNo_retrait(keyHolder.getKey().intValue());
        }
    }

    /**
     * Retrieves all Retrait entities from the database.
     *
     * @return A list containing all Retrait entities.
     */
    @Override
    public List<Retrait> getAll() {
        return jdbcTemplate.query(SELECT, rowMapper);
    }
}

