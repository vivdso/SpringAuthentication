package com.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by vxd4 on 2/19/2017.
 */

@Component
public class JwtTokenUtil {


    private static final long serialVersionUID = -3301605591108950415L;
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_USERDETAILS = "userdetails";
    static final String CLAIM_KEY_CREATED = "created";

    //userConstants
    static final String USER_NAME = "username";
    static final String PASSWORD = "password";
    static final String AUTHORITIES = "authorities";
    static final String ENABLED = "enabled";

    static final String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";
    static final String ACCOUNT_NON_LOCKED = "accountNonLocked";
    static final String ACCOUNT_NON_EXPIRED = "accountNonExpired";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public UserDetails getUserDetailsFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        Object x = claims.get(CLAIM_KEY_USERDETAILS);
        return getUserDetails((LinkedHashMap) x);
    }

    private UserDetails getUserDetails(LinkedHashMap collection) {
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        ArrayList authorityCollection = ((ArrayList) (collection.get(AUTHORITIES)));
        for (int i = 0; i < authorityCollection.size(); i++) {
            authorities.add(new SimpleGrantedAuthority((String) (((LinkedHashMap) authorityCollection.get(i)).get("authority"))));
        }
        return new User(collection.get(USER_NAME).toString(), collection.get(PASSWORD).toString(), (Boolean) collection.get(ENABLED), (Boolean) collection.get(ACCOUNT_NON_EXPIRED), (Boolean) collection.get(CREDENTIALS_NON_EXPIRED), (Boolean) collection.get(ACCOUNT_NON_LOCKED),
                authorities);
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_USERDETAILS, userDetails);
        //claims.put(CLAIM_KEY_AUDIENCE, generateAudience());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserDetails user = userDetails;
        final String username = getUsernameFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token));
    }

    public Boolean validateToken(String token) {
        final Date created = getCreatedDateFromToken(token);
        return (!isTokenExpired(token));

    }

}
