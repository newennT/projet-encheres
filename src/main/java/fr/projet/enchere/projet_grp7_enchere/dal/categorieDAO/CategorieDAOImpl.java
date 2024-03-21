package fr.projet.enchere.projet_grp7_enchere.dal.categorieDAO;

import fr.projet.enchere.projet_grp7_enchere.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the CategorieDAO interface responsible for handling database operations related to categories.
 */
@Repository
class CategorieDAOImpl implements CategorieDAO {

    // SQL REQUESTS
    final String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (:libelle)";

    final String SELECT = "SELECT no_categorie, libelle FROM CATEGORIES";

    final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CategorieDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for mapping database rows to Categorie objects
    RowMapper<Categorie> rowMapper = (rs, i) ->
            new Categorie(
                    rs.getInt("no_categorie"),
                    rs.getString("libelle")
            );

    /**
     * Inserts a new category into the database.
     *
     * @param categorie The category to be inserted.
     */
    @Override
    public void insert(Categorie categorie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("libelle", categorie.getLibelle());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
            categorie.setNo_categorie(keyHolder.getKey().intValue());
        }
    }

    /**
     * Retrieves a list of all categories from the database.
     *
     * @return A list containing all categories.
     */
    @Override
    public List<Categorie> getAll() {
        return jdbcTemplate.query(SELECT, rowMapper);
    }

    /**
     * Retrieves a category from the database based on its ID.
     *
     * @param id The ID of the category to be retrieved.
     * @return The category object matching the provided ID.
     */
    @Override
    public Categorie getById(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        return jdbcTemplate.queryForObject(SELECT_BY_ID, parameters, rowMapper);
    }
}
