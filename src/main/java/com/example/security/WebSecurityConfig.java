package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by vxd4 on 2/16/2017.
 */
@Configuration
@ComponentScan("com.example")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {




    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("user").roles("USER");

        authenticationManagerBuilder.authenticationProvider(getCustomAuthenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //.httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/customer").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/order").hasRole("USER")
                .anyRequest().authenticated().and()

                .csrf().disable();
//
//        http
//                .httpBasic().and()
//                .authorizeRequests().anyRequest().authenticated().and()
//                //.antMatchers("/**").authenticated().and()
//                //.antMatchers(HttpMethod.POST, "/customer").hasRole("ADMIN")
//                //.antMatchers(HttpMethod.PUT, "/customer/**").hasRole("ADMIN")
//                //.antMatchers(HttpMethod.GET, "/customer/**").hasRole("ADMIN").and()
//                .csrf().disable();
    }

    @Bean
    protected CustomAuthenticationProvider getCustomAuthenticationProvider(){
        return new CustomAuthenticationProvider();
    }

}
