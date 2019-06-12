package com.zhouzhu.springbootsecurity.demo.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-12 17:48
 */
@Component
public class JwtTokenProvider {
    private static final Logger logger=LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal=(UserPrincipal)authentication.getPrincipal();
        Date now=new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public Long getUserIdFormJWT(String token){
            Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
            return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Invalid JWT signature");
        } catch (UnsupportedJwtException e) {
            logger.error("Invalid JWT Token");
        } catch (MalformedJwtException e) {
            logger.error("Expired JWT Token");
        } catch (SignatureException e) {
            logger.error("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty");
        }
        return false;
    }
}
