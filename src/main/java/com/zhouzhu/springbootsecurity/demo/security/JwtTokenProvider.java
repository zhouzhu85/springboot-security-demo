package com.zhouzhu.springbootsecurity.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-12 17:48
 */
@Component
public class JwtTokenProvider {
    private static final Logger logger=LoggerFactory.getLogger(JwtTokenProvider.class);

//    @Valid("${app.jwtSecret}")
//    private String jwtSecret;
//
//    @Valid("${app.jwtExpirationInMs}")
//    private int jwtExpirationInMs;
//
//    public String generateToken(Authentication authentication){
//        UserPrincipal userPrincipal=(UserPrincipal)authentication.getPrincipal();
//        Date now=new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//        return Jwts.builder()
//                .setSubject(Long.toString(userPrincipal.getId()))
//                .setIssuedAt(new Date())
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512,jwtSecret)
//                .compact();
//    }
}
