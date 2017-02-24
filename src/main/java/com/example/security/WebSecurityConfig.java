package com.example.security;

import com.example.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by vxd4 on 2/16/2017.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


   @Autowired
   private  UserDetailsService userDetailsService;

   @Autowired
   private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("user").roles("USER");

        //authenticationManagerBuilder.authenticationProvider(getCustomAuthenticationProvider());
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());

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
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).and()
                .csrf().disable();
//
//        http
//                .httpBasic().and()
//                .authorizeRequests().anyRequest().authenticated().and()
//                //.antMatchers("/**").authenticated().and()
//                //.antMatchers(HttpMethod.POST, "/customer").hasRole("ADMIN")
//                //.antMatchers(HttpMethod.PUT, "/customer/**").hasRole("ADMIN")
//                //.antMatchers(HttpMethod.GET, "/customer/**").hasRole("ADMIN").and()
//
//
//               .csrf().disable();

        http.addFilterBefore(authenticationTokenFilterBean(),UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CustomAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new CustomAuthenticationTokenFilter();
    }


    @Bean
    protected CustomAuthenticationProvider getCustomAuthenticationProvider(){
        return new CustomAuthenticationProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
