package com.example.touroperators.security.Jwt;

import com.example.touroperators.models.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUserName()))
                .setIssuer("something")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validate(String token){

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("JWT expired "+ ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("Token is null, empty or only whitespace "+ ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("JWT is invalid "+ ex);
        } catch (UnsupportedJwtException ex) {
            log.error("JWT is not supported "+ ex);
        } catch (SignatureException ex) {
            log.error("Signature validation failed");
        }

        return false;
    }

    public String getUserNameFromToken(String token) {

        String[] jwtSubject = getSubject(token).split(",");
        return jwtSubject[1];
    }

    private String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
