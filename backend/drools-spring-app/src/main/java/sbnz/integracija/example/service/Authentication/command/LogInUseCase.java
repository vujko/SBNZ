package sbnz.integracija.example.service.Authentication.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import sbnz.integracija.example.facts.Authority;
import sbnz.integracija.example.facts.validation.SelfValidating;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface LogInUseCase {

    public LoginDTO logIn(LoginCommand command);

    @Value
    class LoginCommand extends SelfValidating<LoginCommand> {
        
    	@Email    
        String email;
        @NotBlank
        String password;

        public LoginCommand(
                String email,
                String password) {
            this.email = email;
            this.password = password;
            this.validateSelf();
        }
    }


    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    class LoginDTO {
        String token;
        List<String> roles;
        long expiresIn;

        public static LoginDTO of(
                String token,
                Set<Authority> authorities,
                long expiresIn) {
            return new LoginDTO(token, mapRoles(authorities), expiresIn);
        }

        private static List<String> mapRoles(Set<Authority> authorities) {
            return authorities
                    .stream()
                    .map(Authority::getName)
                    .collect(Collectors.toList());
        }
    }

}
