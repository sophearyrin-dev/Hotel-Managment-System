package cs.miu.edu.security.jwt;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${subo8.app.jwtSecret}")
  private String jwtSecret;

 // @Value("${subo8.app.jwtExpirationMs}")
  //private int jwtExpirationMs;

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
//    String jwt = generateTokenFromUsername(userPrincipal.getUsername(), userPrincipal.getId());
//    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/").maxAge(24 * 60 * 60).httpOnly(true).build();
//    return cookie;
//  }

  public ResponseCookie getCleanJwtCookie() {
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/").build();
    return cookie;
  }

  //Get id from jwt cookie
  public String getUserNameFromJwtToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

//  public boolean validateJwtToken(String authToken) {
//    try {
//      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//      return true;
//    } catch (SignatureException e) {
//      logger.error("Invalid JWT signature: {}", e.getMessage());
//    } catch (MalformedJwtException e) {
//      logger.error("Invalid JWT token: {}", e.getMessage());
//    } catch (ExpiredJwtException e) {
//      logger.error("JWT token is expired: {}", e.getMessage());
//    } catch (UnsupportedJwtException e) {
//      logger.error("JWT token is unsupported: {}", e.getMessage());
//    } catch (IllegalArgumentException e) {
//      logger.error("JWT claims string is empty: {}", e.getMessage());
//    }
//
//    return false;
//  }
  
//  public String generateTokenFromUsername(String username, String userid) {
//    return Jwts.builder()
//        .setSubject(username).claim("userId", userid)
//        .setIssuedAt(new Date())
//        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//        .signWith(SignatureAlgorithm.HS512, jwtSecret)
//        .compact();
//  }
}
