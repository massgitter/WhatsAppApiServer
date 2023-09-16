package com.barbarahause.whatsappapiserver.controllers;

import com.barbarahause.whatsappapiserver.configs.JwtUtils;
import com.barbarahause.whatsappapiserver.requests.LoginRequest;
import com.barbarahause.whatsappapiserver.requests.UserRequest;
import com.barbarahause.whatsappapiserver.responses.UsersResponse;
import com.barbarahause.whatsappapiserver.service.UserDetailsImpl;
import com.barbarahause.whatsappapiserver.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Users")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final JwtUtils jwtUtils;
    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/create")
    @Operation(summary = "First of all You should create at least two users to chat with each other!")
    public UsersResponse saveUser(@RequestBody UserRequest request) {
        return usersService.saveUser(request);
    }

    @PostMapping("/login")
    @Operation(summary = "After users are being created one can login and send messages!")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new UsersResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()));
    }
}
