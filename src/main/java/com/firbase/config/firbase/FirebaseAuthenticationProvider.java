package com.firbase.config.firbase;



import com.firbase.config.domain.FirebaseUser;
import com.firbase.config.jwt.JwtAuthenticationEntryPoint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class FirebaseAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();

        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            FirebaseUser user = new FirebaseUser(decodedToken); // You need to implement FirebaseUser class
            return new JwtAuthenticationEntryPoint.FirebaseAuthenticationToken(user, null);
        } catch (FirebaseAuthException e) {
            throw new FirebaseAuthenticationException("Firebase authentication failed", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationEntryPoint.FirebaseAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
