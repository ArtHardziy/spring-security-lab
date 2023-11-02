package by.es.springsecurity.controller;

import by.es.springsecurity.model.dto.JwtAuthenticationResponse;
import by.es.springsecurity.model.dto.SignInRequest;
import by.es.springsecurity.model.dto.SignUpRequest;
import by.es.springsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signup(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}
