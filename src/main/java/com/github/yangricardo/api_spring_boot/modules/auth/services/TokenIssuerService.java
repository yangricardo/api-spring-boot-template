package com.github.yangricardo.api_spring_boot.modules.auth.services;

import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenIssuerService {

  private String expiration;
  private String secret;
  private String issuer;

  @Autowired
  public TokenIssuerService(
    @Value("${jwt.expiration}") String expiration,
    @Value("${jwt.issuer}") String issuer,
    @Value("${jwt.secret}") String secret
  ) {
    this.expiration = expiration;
    this.secret = secret;
    this.issuer = issuer;
  }

  public String generateToken(Authentication authentication) {
    User user = (User) authentication.getPrincipal();

    Date now = new Date();
    Date exp = new Date(now.getTime() + Long.parseLong(expiration));

    return Jwts
      .builder()
      .setIssuer(this.issuer)
      .setSubject(user.getId().toString())
      .setIssuedAt(new Date())
      .setExpiration(exp)
      .signWith(SignatureAlgorithm.HS256, secret)
      .compact();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Long getTokenId(String token) {
    Claims body = Jwts
      .parser()
      .setSigningKey(secret)
      .parseClaimsJws(token)
      .getBody();
    return Long.valueOf(body.getSubject());
  }
}
