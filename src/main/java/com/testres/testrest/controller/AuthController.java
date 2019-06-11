package com.testres.testrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.testres.testrest.service.AuthenticationRequest;
import com.testres.testrest.service.IUserService;

/**
 * @author konst
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IUserService userService;

    @Autowired
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AuthenticationRequest data) {
        return ResponseEntity.ok(userService.signin(data));
    }


    @GetMapping("/me")
    public ResponseEntity<?> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.currentUser(userDetails));
    }
}
