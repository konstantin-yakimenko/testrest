package com.testres.testrest.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.testres.testrest.exception.NotFoundException;
import com.testres.testrest.model.Interest;
import com.testres.testrest.repository.IInterestRepository;

import java.util.List;

/**
 * @author konst
 */
@Repository
public class InterestRepository implements IInterestRepository {

    private static final Logger log = LoggerFactory.getLogger(InterestRepository.class);

    private final NamedParameterJdbcTemplate npjt;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InterestRepository(NamedParameterJdbcTemplate npjt, JdbcTemplate jdbcTemplate) {
        this.npjt = npjt;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Interest save(Interest interest) {
        MapSqlParameterSource map  = new MapSqlParameterSource()
                .addValue("title", interest.getTitle())
                .addValue("person_id", interest.getPersonId())
                .addValue("interest_id", interest.getInterestId());
        return interest.getInterestId() == 0L
                ?  add(interest, map)
                : update(interest, map);
    }

    private Interest add(Interest interest, MapSqlParameterSource map) {
        Long newId = npjt.queryForObject("" +
                "insert into interest.interest(person_id, title) " +
                " values(:person_id, :title) " +
                " RETURNING interest_id", map, (rs, rowNum) -> rs.getLong("interest_id"));
        interest.setInterestId(newId);
        log.info("Insert {}", interest);
        return interest;
    }

    private Interest update(Interest interest, MapSqlParameterSource map) {
        int count = npjt.update("" +
                "update interest.interest" +
                " set person_id = :person_id," +
                " title = :title" +
                " where interest_id = :interest_id", map);
        log.info("Update {} interest", count);
        return interest;
    }

    @Override
    @Transactional
    public Interest delete(Interest interest) {
        int deleteCount = npjt.update("delete from interest.interest where interest_id = :interest_id",
                new MapSqlParameterSource().addValue("interest_id", interest.getInterestId()));
        log.info("Delete interest count: {}", deleteCount);
        return interest;
    }

    @Override
    @Transactional(readOnly = true)
    public Interest findById(Long id) throws NotFoundException {
        List<Interest> interest = jdbcTemplate.query("select interest_id, person_id, title from interest.interest where interest_id = " + id,
                (rs, rowNum) -> new Interest(rs.getLong("interest_id"), rs.getLong("person_id"), rs.getString("title")));
        if (interest.isEmpty()) {
            throw new NotFoundException("Not found interest " + id);
        }
        return interest.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Interest> findByPerson(Long id) {
        return jdbcTemplate.query("select interest_id, person_id, title from interest.interest where person_id = " + id,
                (rs, rowNum) -> new Interest(rs.getLong("interest_id"), rs.getLong("person_id"), rs.getString("title")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Interest> findByTitle(String title) {
        return jdbcTemplate.query("" +
                        "select interest_id, person_id, title " +
                        " from interest.interest " +
                        " where title like '%'"+title+"'%'",
            (rs, rowNum) -> new Interest(rs.getLong("interest_id"), rs.getLong("person_id"), rs.getString("title")));
    }
}
