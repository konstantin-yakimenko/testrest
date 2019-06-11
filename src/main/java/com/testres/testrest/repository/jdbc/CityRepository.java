package com.testres.testrest.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.testres.testrest.model.City;
import com.testres.testrest.repository.ICityRepository;

import java.util.List;

/**
 * @author konst
 */
@Repository
@Transactional(readOnly = true)
public class CityRepository implements ICityRepository {

    private static final Logger log = LoggerFactory.getLogger(CityRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query("select city_id, title from interest.city",
                (rs, rowNum) -> new City(rs.getLong("city_id"), rs.getString("title")));
    }

    @Override
    public City findById(Long id) {
        List<City> list = jdbcTemplate.query("select city_id, title from interest.city where city_id = " + id,
                (rs, rowNum) -> new City(rs.getLong("city_id"), rs.getString("title")));
        return !list.isEmpty()
                ? list.get(0)
                : null;
    }

    @Override
    public List<City> findByTitle(String title) {
        return jdbcTemplate.query("select city_id, title from interest.city where title like '%"+title+"%'",
                (rs, rowNum) -> new City(rs.getLong("city_id"), rs.getString("title")));
    }
}
