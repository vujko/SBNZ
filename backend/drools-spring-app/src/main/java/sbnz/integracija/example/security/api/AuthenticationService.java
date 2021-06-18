package sbnz.integracija.example.security.api;

import sbnz.integracija.example.facts.User;

public interface AuthenticationService {
    User authenticate(String email, String password);
    User getAuthenticated();
}
