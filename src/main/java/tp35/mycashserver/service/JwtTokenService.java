package tp35.mycashserver.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    private final Algorithm hmac256;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${jwt.secret}") final String secret) {
        this.hmac256 = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(this.hmac256).build();
    }

    public String generateToken(final UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .sign(this.hmac256);
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            System.out.println("token invalid: " + verificationEx.getMessage());
            return null;
        }
    }

}
