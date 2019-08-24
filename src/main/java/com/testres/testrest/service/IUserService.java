package com.testres.testrest.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author konst
 */
public interface IUserService {

    AuthenticationResponse signin(AuthenticationRequest data);

    UserInfo currentUser(UserDetails userDetails);

    void create();
}

