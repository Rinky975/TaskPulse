package taskpulse.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "taskpulsejwtsecretkeytaskpulse123456";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ✅ Generate Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hrs
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract Email safely
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ Validate Token (IMPORTANT FIX)
    public boolean isTokenValid(String token, String email) {
        try {
            String username = extractUsername(token);
            return username.equals(email) && !isTokenExpired(token);
        } catch (ExpiredJwtException e) {
            System.out.println("JWT expired");
            return false;
        } catch (Exception e) {
            System.out.println("Invalid JWT");
            return false;
        }
    }

    // ✅ Check expiration safely
    private boolean isTokenExpired(String token) {
        try {
            return extractAllClaims(token)
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    // ✅ Extract claims safely
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}