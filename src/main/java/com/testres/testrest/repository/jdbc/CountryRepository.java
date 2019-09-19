package com.testres.testrest.repository.jdbc;

import com.testres.testrest.model.Country;
import com.testres.testrest.repository.ICountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author konst
 */
@Repository
public class CountryRepository implements ICountryRepository {

    private static final Logger log = LoggerFactory.getLogger(CountryRepository.class);

    private final NamedParameterJdbcTemplate npjt;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryRepository(NamedParameterJdbcTemplate npjt, JdbcTemplate jdbcTemplate) {
        this.npjt = npjt;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Country save(@NotNull Country country) {
        MapSqlParameterSource map  = new MapSqlParameterSource()
                .addValue("title", country.getTitle())
                .addValue("country_id", country.getCountryId());
        return country.getCountryId() == 0L
                ? add(country, map)
                : update(country, map);
    }

    private Country update(Country country, MapSqlParameterSource map) {
        Integer count = npjt.update("" +
                "update interest.country" +
                " set title = :title" +
                " where country_id = :country_id", map);
        log.info("Update {} country", count);
        return country;
    }

    private Country add(Country country, MapSqlParameterSource map) {
        Number newId = new SimpleJdbcInsert(jdbcTemplate)
                .withSchemaName("interest")
                .withTableName("country")
                .usingGeneratedKeyColumns("country_id")
                .executeAndReturnKey(map);
        return new Country(newId.longValue(), country.getTitle());
    }

    @Override
    @Transactional
    public Country delete(@NotNull Country country) {
        int update = npjt.update("delete from interest.country where country_id = :country_id",
                new MapSqlParameterSource().addValue("country_id", country.getCountryId()));
        log.info("Delete counry count: {}", update);
        return country;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        log.info("Load all countries from base");
        return jdbcTemplate.query("select country_id, title from interest.country",
                (rs, rowNum) -> new Country(rs.getLong("country_id"), rs.getString("title")));
    }

    @Override
    @Transactional(readOnly = true)
    public Country findById(Long id) {
        List<Country> list = jdbcTemplate.query("select country_id, title from interest.country where country_id = " + id,
                (rs, rowNum) -> new Country(rs.getLong("country_id"), rs.getString("title")));
        return list.isEmpty()
                ? null
                : list.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Country> findBySearchTerm(@NotNull String searchTerm, @NotNull Pageable pageable) {
        List<Country> list = jdbcTemplate.query("" +
                        "select country_id, title " +
                        " from interest.country " +
                        " where title like '%'"+searchTerm+"'%' limit "+pageable.getPageSize()+" offset "+pageable.getOffset(),
                (rs, rowNum) -> new Country(rs.getLong("country_id"), rs.getString("title")));
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    @Transactional(readOnly = true)
    public Country getCountry(Long id) {
        log.info("getCountry from base {}", id);
        return npjt.queryForObject("select country_id, title from interest.country where country_id = :id"
        , new MapSqlParameterSource().addValue("id", id),
                (rs, rowNum) -> new Country(rs.getLong("country_id"), rs.getString("title") ));
    }
}
