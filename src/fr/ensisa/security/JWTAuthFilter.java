package fr.ensisa.security;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@SigninNeeded
public class JWTAuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

    }

    private String validate(String jwt) throws InvalidJwtException {
        // Creates subject
        String subject = null;

        // Produce RsaJsonWeb Key
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();

        // Creates JWT decoder
        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireSubject()
                .setVerificationKey(rsaJsonWebKey.getKey())
                .build();

        // Validate JWT and Get content
        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        subject = (String) jwtClaims.getClaimValue("sub");

        return subject;
    }

}
