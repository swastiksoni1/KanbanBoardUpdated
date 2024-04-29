package com.bej.authentication.security;


import com.bej.authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public String createToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        return generateToken(claims,user.getUserId());
    }

    public String generateToken(Map<String,Object> claims,String subject) {
        String jwtToken = Jwts.builder().setIssuer("TaskZone")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mysecret")
                //mysecret is the key that has to be shared everytime you do encrypt and decrypt process
                .compact();
        return jwtToken;

    }


}
