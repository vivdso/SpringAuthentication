package com.example.controller;

import com.example.Service.UserAccountService;

import com.example.domain.JwtAuthenticationRequest;
import com.example.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vxd4 on 2/19/2017.
 */

@RestController
@RequestMapping("/auth")
public class Auth {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private JwtTokenUtil jwtTokentUtil;

    @RequestMapping(method = RequestMethod.POST)
    public String authenticate(@RequestBody JwtAuthenticationRequest authenticationRequest){

        final Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userAccountService.loadUserByUsername(authentication.getName());
        String token = jwtTokentUtil.generateToken(userDetails);
        return token;

    }
}
