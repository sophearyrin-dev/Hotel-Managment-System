package com.sa.finalproject.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${subo8.app.jwtSecret}")
    private String jwtSecret;

    @Value("${subo8.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${subo8.app.jwtCookieName}")
    private String jwtCookie;

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

//  public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
//
//   List<?> list = new ArrayList<>(userPrincipal.getAuthorities());
//
//   for(int i=0; i<list.size(); i++) {
//     System.out.println(list.get(i).toString());
//     if (list.get(i).toString() == "ROLE_USER") {
//       System.out.println("yes");
//     } else {
//       System.out.println("no");
//     }
//   }
//
//    String jwt = generateTokenFromUsername(userPrincipal.getUsername(), userPrincipal.getId(), list);
//    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/").maxAge(24 * 60 * 60).httpOnly(true).build();
//    return cookie;
//  }

    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/").build();
        return cookie;
    }

    //Get username and id from jwt cookie
    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//    String print1 = claims.getSubject(); //username
//    String print2 = (String) claims.get("userId");
//    System.out.println(print1);
//    System.out.println(print2);
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    // Get userRole from JWT
    public String getUserRoleFromJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String generateTokenFromUsername(String username, String userid, List roles) {
        return Jwts.builder()
                .setSubject(username).claim("userId", userid).claim("role", roles.get(0).toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
