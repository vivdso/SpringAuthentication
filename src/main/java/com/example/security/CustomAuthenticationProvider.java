package com.example.security;

import com.example.Data.UserAccountDbRepository;
import com.example.Service.UserAccountService;
import com.example.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by vxd4 on 2/19/2017.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userAccountService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        UserDetails userAccount = userAccountService.loadUserByUsername(name);
        if (userAccount!=null && name.equals(userAccount.getUsername()) && getPassordEncoder().matches(authentication.getCredentials().toString(),userAccount.getPassword()) )
        {
            Authentication auth = new UsernamePasswordAuthenticationToken(name,  authentication.getCredentials().toString(), userAccount.getAuthorities());
            return auth;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Bean
    private BCryptPasswordEncoder getPassordEncoder(){return  new BCryptPasswordEncoder();}
}
