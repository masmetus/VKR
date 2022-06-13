package com.example.VKR.controllers;

import com.example.VKR.dto.auth.request.LoginRequest;
import com.example.VKR.dto.auth.request.SignRequest;
import com.example.VKR.dto.auth.response.JwtResponse;
import com.example.VKR.dto.auth.response.MessageResponse;
import com.example.VKR.model.Role;
import com.example.VKR.model.User;
import com.example.VKR.repository.RoleRepository;
import com.example.VKR.repository.UserRepository;
import com.example.VKR.security.UserDetailsImpl;
import com.example.VKR.security.jwt.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reg")
public class RegisterController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    @ApiOperation("Вход")
    public ResponseEntity<JwtResponse> authUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }


    @PostMapping("/register")
    @ApiOperation("Регистрация")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignRequest signRequest) {
        if (userRepository.existsByUsername(signRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: username is already taken!"));
        }
        //Create new user
        User user = new User(
                signRequest.getUsername(),
                signRequest.getEmail(),
                signRequest.getFirstName(),
                signRequest.getLastName(),
                encoder.encode(signRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.getById((long) 5));
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User Registered"));
    }

}

