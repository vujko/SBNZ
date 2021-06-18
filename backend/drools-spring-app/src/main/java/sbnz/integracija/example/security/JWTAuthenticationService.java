package sbnz.integracija.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import sbnz.integracija.example.facts.User;
import sbnz.integracija.example.security.api.AuthenticationService;

@Component
public class JWTAuthenticationService implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JWTAuthenticationService(AuthenticationManager authenticationManager,
    		PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return getPrincipal(authentication);
    }

    @Override
    public User getAuthenticated() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return getPrincipal(currentUser);
    }

    private User getPrincipal(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
