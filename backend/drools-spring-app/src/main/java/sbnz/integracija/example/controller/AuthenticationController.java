package sbnz.integracija.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.dto.LoginRequest;
import sbnz.integracija.example.service.Authentication.command.LogInUseCase;

@RestController
@RequestMapping(value = "api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LogInUseCase logInUseCase;

    @PostMapping("/login")
    public ResponseEntity<LogInUseCase.LoginDTO> login(@RequestBody LoginRequest request) {

        LogInUseCase.LoginCommand command =
                new LogInUseCase.LoginCommand(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(logInUseCase.logIn(command));
    }

}
