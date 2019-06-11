package com.testres.testrest.repository;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author konst
 */
public interface IUserRepository {

    UserDetails save(UserDetails user);

    UserDetails delete(UserDetails user);

    UserDetails findById(Long id);

    Boolean existsByUsername(String username);

    UserDetails findByUsername(String username);

    void deleteByUsername(String username);

}
