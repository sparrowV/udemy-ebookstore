package ge.odvali.ebookstore.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.expiration.time.in.millis}")
    private Long expirationTime;


    public String generateJwtToken(String username) {
        return JWT.create()
                .withIssuedAt(new Date()).withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(HMAC512(secret.getBytes()));
    }

    public Authentication getAuthentication(String token, HttpServletRequest request) {
        boolean validToken = isTokenValid(token);
        if (!validToken) throw new JWTVerificationException("cant decode the token");

        String username = getSubject(token);
        UsernamePasswordAuthenticationToken userPasswordAuthToken = new
                UsernamePasswordAuthenticationToken(username, null, null);
        userPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return userPasswordAuthToken;
    }

    public boolean isTokenValid(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return !isTokenValidHelper(verifier, token);
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    private boolean isTokenValidHelper(JWTVerifier verifier, String token) {

        DecodedJWT decodedJwt = verifier.verify(token); //if it cant decode, throws an exception
        Date expiration = decodedJwt.getExpiresAt(); //it means token is valid
        return expiration.before(new Date()); //check if its not expired

    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = HMAC512(secret);
            verifier = JWT.require(algorithm).build();

        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("cant instantiate jwt verifier");
        }
        return verifier;
    }
}
