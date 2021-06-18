package sbnz.integracija.example.security;

import sbnz.integracija.example.facts.User;
import sbnz.integracija.example.security.api.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenService implements TokenService {
    private final TokenUtils tokenUtils;

    @Autowired
    public JWTTokenService(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    public String getToken(User user) {
        return tokenUtils.generateToken(user.getEmail());
    }

    @Override
    public long getExpiresIn() {
        return tokenUtils.getExpiredIn();
    }
}
