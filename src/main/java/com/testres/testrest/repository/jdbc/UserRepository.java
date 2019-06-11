package com.testres.testrest.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.testres.testrest.repository.IUserRepository;
import com.testres.testrest.util.ZonedDateTimeConverter;

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
        if (id == 1L) {
            return User.withUsername("admin").password("{noop}adminpass").roles("USER", "ADMIN").build();
        } else if (id == 2L) {
            return User.withUsername("user").password("{noop}userpass").roles("USER").build();
        }
        throw new IllegalArgumentException("Not found user");
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByUsername(@NotNull String username) {
        if (username.equals("admin") || username.equals("user")) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails findByUsername(@NotNull String username) {
        if (username.equals("admin")) {
            return User.withUsername("admin").password("{noop}adminpass").roles("USER", "ADMIN").build();
        } else if (username.equals("user")) {
            return User.withUsername("user").password("{noop}userpass").roles("USER").build();
        }
        throw new IllegalArgumentException("Not found user");
    }

    @Override
    @Transactional
    public void deleteByUsername(@NotNull String username) {
    }
}
