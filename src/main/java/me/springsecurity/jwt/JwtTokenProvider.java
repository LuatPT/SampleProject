package me.springsecurity.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import me.springsecurity.user.CustomUserDetail;

@Component
@Slf4j
public class JwtTokenProvider {

	 // private string key only sever know
    private final String JWT_SECRET = "keyprivate";
    //expired
    private final long JWT_EXPIRATION = 604800000L;
    
    // Create jwt with user information
    public String generateToken(CustomUserDetail userDetail) {
    	
    	Date now = new Date();
    	Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
    	
    	//create jwt string with user id
    	return Jwts.builder()
    			.setSubject(	userDetail.getUser().getUsername()		)
    			.setIssuedAt(now)
    			.setExpiration(expiryDate)
    			.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
    			.compact();
    }
    
    // get information from jwt
    public Long getUserIdFromJWT(String token) {
    	Claims claims = Jwts.parser()
    						.setSigningKey(JWT_SECRET)
    						.parseClaimsJws(token)
    						.getBody();
    	return Long.parseLong(claims.getSubject());
    }
    
    public boolean validateToken(String authToken) {
    	 try {
	    	Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
	    	return true;
    	 }catch (MalformedJwtException ex) {
             log.error("Invalid JWT token");
         } catch (ExpiredJwtException ex) {
             log.error("Expired JWT token");
         } catch (UnsupportedJwtException ex) {
             log.error("Unsupported JWT token");
         } catch (IllegalArgumentException ex) {
             log.error("JWT claims string is empty.");
         }
    	return false;
    }
}
