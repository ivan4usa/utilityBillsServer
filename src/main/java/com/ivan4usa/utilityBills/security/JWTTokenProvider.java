package com.ivan4usa.utilityBills.security;

import com.google.gson.Gson;
import com.ivan4usa.utilityBills.entities.User;
import com.ivan4usa.utilityBills.payloads.LoginResponse;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenProvider {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Value("${jwt.long_expiration_time}") private int LONG_EXPIRATION_TIME;
    @Value("${jwt.short_expiration_time}") private int SHORT_EXPIRATION_TIME;
    @Value("${jwt.claim_user_key}") private String CLAIM_USER_KEY;
    @Value("${jwt.secret_key}") private String SECRET_KEY;
    @Value("${jwt.token_prefix}") private String TOKEN_PREFIX;

    public LoginResponse generateToken(User user, boolean longExpirationMode) {
        Date now = new Date();
        int exp;
        if (longExpirationMode) {
            exp = LONG_EXPIRATION_TIME;
        } else {
            exp = SHORT_EXPIRATION_TIME;
        }
        Date expirationDate = new Date(System.currentTimeMillis() + exp);
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put(CLAIM_USER_KEY, user);
        claimMap.put(Claims.SUBJECT, user.getId());

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

        return new LoginResponse(TOKEN_PREFIX + Jwts.builder()
                .setClaims(claimMap)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact(), null, System.currentTimeMillis() + exp);
    }

    public boolean validateToken(String token) {
        System.out.println(token);
        try {
            Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: " + token);
        } catch (SignatureException e) {
            logger.error("JWT token is not signed: " + token);
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: " + token);
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: " + token);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: " + token);
        } catch (NullPointerException e) {
            logger.error("Null Pointer: " + token);
        }
        return false;
    }

    public User getUserFromToken(String token) throws IllegalArgumentException {
        String json = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get(CLAIM_USER_KEY).toString();
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }
}
