package com.testres.testrest.service;

import java.util.List;

/**
 * @author konst
 */
public class UserInfo {
    private String username;
    private List<String> roles;

    public UserInfo() {
    }

    public UserInfo(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
