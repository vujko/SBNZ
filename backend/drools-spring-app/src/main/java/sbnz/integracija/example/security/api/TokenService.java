package sbnz.integracija.example.security.api;

import sbnz.integracija.example.facts.User;

public interface TokenService {
    String getToken(User user);

    long getExpiresIn();
}
