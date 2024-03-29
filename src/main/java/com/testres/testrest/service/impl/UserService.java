package com.testres.testrest.service.impl;

import com.testres.testrest.repository.IUserRepository;
import com.testres.testrest.security.JwtTokenProvider;
import com.testres.testrest.service.AuthenticationRequest;
import com.testres.testrest.service.AuthenticationResponse;
import com.testres.testrest.service.IUserService;
import com.testres.testrest.service.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

/**
 * @author konst
 */
@Service
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, IUserRepository userRepository, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public AuthenticationResponse signin(@NotNull AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            log.info("signin: {}", username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username,
                    userRepository.findByUsername(username).getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()));

            return new AuthenticationResponse(username, token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @Override
    public UserInfo currentUser(@NotNull UserDetails userDetails) {
        log.info("Get info about current user: {}", userDetails.getUsername());
        return new UserInfo(userDetails.getUsername(),
                userDetails.getAuthorities()
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void create() {
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("adminpass"))
                .roles("USER", "ADMIN")
                .build();
        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("userpass"))
                .roles("USER")
                .build();

        userRepository.save(admin);
        userRepository.save(user);
    }
}
