package com.testres.testrest.repository.jdbc;

import com.testres.testrest.repository.IUserRepository;
import com.testres.testrest.util.ZonedDateTimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * @author konst
 */
@Repository
public class UserRepository implements IUserRepository {

    private static final Logger log =   LoggerFactory.getLogger(UserRepository.class);

    private final NamedParameterJdbcTemplate npjt;
    private final JdbcTemplate jdbcTemplate;
    private final ZonedDateTimeConverter converter;

    public UserRepository(NamedParameterJdbcTemplate npjt, JdbcTemplate jdbcTemplate, ZonedDateTimeConverter converter) {
        this.npjt = npjt;
        this.jdbcTemplate = jdbcTemplate;
        this.converter = converter;
    }

    @Override
    @Transactional
    public UserDetails save(@NotNull UserDetails user) {
        String sql =
                "insert into interest.users(username, password) values(:username, :password)";
        MapSqlParameterSource params  = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword());
        npjt.update(sql, params);
        return user;
    }

    @Override
    @Transactional
    public UserDetails delete(@NotNull UserDetails user) {
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails findById(Long id) {
        log.info("--------------findById");
        return npjt.queryForObject("select username, password from interest.users where user_id = :user_id",
                new MapSqlParameterSource().addValue("user_id", id),
                (rs, rowNum) -> {
                    return User
                            .withUsername(rs.getString("username"))
                            .password(rs.getString("password"))
                            .roles("USER", "ADMIN")
                            .build();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByUsername(@NotNull String username) {
        log.info("--------------existsByUsername");
        UserDetails userDetails = npjt.queryForObject("select username, password from interest.users where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                (rs, rowNum) -> {
                    return User
                            .withUsername(rs.getString("username"))
                            .password(rs.getString("password"))
                            .roles("USER", "ADMIN")
                            .build();
                });
        return userDetails != null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails findByUsername(@NotNull String username) {
        return npjt.queryForObject("select username, password from interest.users where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                (rs, rowNum) -> User
                        .withUsername(rs.getString("username"))
                        .password(rs.getString("password"))
                        .roles("USER", "ADMIN")
                        .build());
    }

    @Override
    @Transactional
    public void deleteByUsername(@NotNull String username) {
    }
}
