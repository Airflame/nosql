package com.nosql.sl;

import com.nosql.dl.model.User;
import com.nosql.dl.repo.UserRepo;
import com.nosql.sl.request.AuthUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthEndpoint {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/auth/signup")
    private ResponseEntity<String> signUp(@RequestBody AuthUserRequest authUserRequest) {
        if (userRepo.existsByUsername(authUserRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(authUserRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(authUserRequest.getPassword()));
        user.setRole("GUEST");
        userRepo.save(user);
        return ResponseEntity.ok("User registered.");
    }

    @PostMapping("/api/auth/login")
    private ResponseEntity<String> login(@RequestBody AuthUserRequest authUserRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authUserRequest.getUsername(), authUserRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("User authenticated.");
    }
}
