package com.example.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by vxd4 on 2/21/2017.
 */
public class CustomUserDetails extends User {

    private String displayName;

    private String email;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String email, String displayName) {
        super(username, password, authorities);
        this.email=email;
        this.displayName=displayName;
    }

    public CustomUserDetails(String username,String password, Collection<? extends GrantedAuthority> authorities,boolean enabled) {
        super(username, password, enabled, true, true, true, authorities);
        this.email=email;
        this.displayName=displayName;

    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
