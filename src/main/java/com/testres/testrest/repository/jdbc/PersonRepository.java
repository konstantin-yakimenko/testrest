package com.testres.testrest.repository.jdbc;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.testres.testrest.repository.IPersonRepository;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.testres.testrest.exception.NotFoundException;
import com.testres.testrest.model.PersonEntity;
import com.testres.testrest.model.QueryParamsPerson;
import com.testres.testrest.repository.IPersonRepository;
import com.testres.testrest.util.ZonedDateTimeConverter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author konst
 */
@Repository
public class PersonRepository implements IPersonRepository {

    private static final Logger log = LoggerFactory.getLogger(PersonRepository.class);

    private final NamedParameterJdbcTemplate npjt;
    private final JdbcTemplate jdbcTemplate;
    private final ZonedDateTimeConverter converter;

    @Autowired
    public PersonRepository(NamedParameterJdbcTemplate npjt, JdbcTemplate jdbcTemplate, ZonedDateTimeConverter converter) {
        this.npjt = npjt;
        this.jdbcTemplate = jdbcTemplate;
        this.converter = converter;
    }


    @Override
    @Transactional
    public PersonEntity save(PersonEntity person) {
        Timestamp birth = (person.getBirth() == null)
                ? null
                : Timestamp.valueOf(person.getBirth().atStartOfDay());
        MapSqlParameterSource map  = new MapSqlParameterSource()
                .addValue("person_id", person.getPersonId())
                .addValue("name", person.getName())
                .addValue("sex", person.getSex())
                .addValue("birth", person.getBirth())
                .addValue("email", person.getEmail())
                .addValue("approve_email", converter.convertToDatabaseColumn(person.getApproveEmail()))
                .addValue("phone_number", person.getPhone())
                .addValue("approve_phone", converter.convertToDatabaseColumn(person.getApprovePhone()))
                .addValue("country_id", person.getCountryId())
                .addValue("city_id", person.getCityId())
                .addValue("status", person.getStatus())
                .addValue("premium_start", converter.convertToDatabaseColumn(person.getPremiumStart()))
                .addValue("premium_finish", converter.convertToDatabaseColumn(person.getPremiumFinish()))
                .addValue("about", person.getAbout());
        return (person.getPersonId() == 0L)
                ? add(person, map)
                : update(person, map);
    }

    private PersonEntity add(PersonEntity person, MapSqlParameterSource map) {
        Long person_id = npjt.queryForObject("" +
                "insert into interest.person (name,sex,birth,email,approve_email,phone_number,approve_phone,country_id,city_id,status,premium_start,premium_finish,about)" +
                " values(:name,cast(:sex as interest.sex),:birth,:email,:approve_email,:phone_number,:approve_phone,:country_id,:city_id,:status,:premium_start,:premium_finish,:about)" +
                " RETURNING person_id", map, (rs, rowNum) -> rs.getLong("person_id"));
        person.setPersonId(person_id);
        log.info("Insert {} person", person);
        return person;
    }

    private PersonEntity update(PersonEntity person, MapSqlParameterSource map) {
        int count = npjt.update("" +
                "update interest.person" +
                " set" +
                " name = :name," +
                " sex = cast(:sex as interest.sex)," +
                " birth = :birth," +
                " email = :email," +
                " approve_email = :approve_email," +
                " phone_number = :phone_number," +
                " approve_phone = :approve_phone," +
                " country_id = :country_id," +
                " city_id = :city_id," +
                " status = :status," +
                " premium_start = :premium_start," +
                " premium_finish = :premium_finish," +
                " about = :about" +
                " where person_id = :person_id", map);
        log.info("Update {} person", count);
        return person;
    }

    @Override
    @Transactional
    public PersonEntity delete(PersonEntity person) {
        int deleteCount = npjt.update("delete from interest.person where person_id = :person_id",
                new MapSqlParameterSource().addValue("person_id", person.getPersonId()));
        log.info("Delete person count: {}", deleteCount);
        return person;
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonEntity findById(Long id) throws NotFoundException {
        List<PersonEntity> persons = jdbcTemplate.query("" +
                "select * " +
                " from interest.person " +
                " where person_id = " + id, (rs, rowNum) ->
                new PersonEntity(
                        rs.getLong("person_id"),
                        rs.getString("name"),
                        rs.getString("sex"),
                        toLocalDate(rs.getTimestamp("birth")),
                        rs.getString("email"),
                        converter.convertToEntityAttribute(rs.getTimestamp("approve_email")),
                        rs.getString("phone_number"),
                        converter.convertToEntityAttribute(rs.getTimestamp("approve_phone")),
                        rs.getLong("country_id"),
                        rs.getLong("city_id"),
                        converter.convertToEntityAttribute(rs.getTimestamp("create_date")),
                        rs.getString("status"),
                        converter.convertToEntityAttribute(rs.getTimestamp("premium_start")),
                        converter.convertToEntityAttribute(rs.getTimestamp("premium_finish")),
                        rs.getString("about"),
                        new ArrayList<>()
                ));

        if (persons.isEmpty()) {
            throw new NotFoundException("Not found person " + id);
        }
        return persons.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonEntity> findByParams(QueryParamsPerson queryParamsPerson) {
        return new ArrayList<>();
    }
}
// https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/jdbc.html
