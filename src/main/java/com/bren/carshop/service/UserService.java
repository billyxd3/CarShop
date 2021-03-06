package com.bren.carshop.service;

import com.bren.carshop.dto.request.UserRequest;
import com.bren.carshop.dto.response.AuthenticationResponse;
import com.bren.carshop.entity.User;
import com.bren.carshop.entity.UserRole;
import com.bren.carshop.repository.UserRepository;
import com.bren.carshop.security.JwtTokenTool;
import com.bren.carshop.security.JwtUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenTool jwtTokenTool;

    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenTool jwtTokenTool, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenTool = jwtTokenTool;
        this.encoder = encoder;
    }

    public AuthenticationResponse register(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadCredentialsException("User with username " + request.getUsername() + " already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUserRole(UserRole.ROLE_USER);
        user.setPassword(encoder.encode(request.getPassword()));

        userRepository.save(user);

        return login(request);
    }

    public AuthenticationResponse login(UserRequest request) {
        String username = request.getUsername();
        User user = findByUsername(username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        UserRole userRole = user.getUserRole();
        String token = jwtTokenTool.createToken(username, userRole);
        String email = request.getEmail();
        String phoneNumber = request.getEmail();
        return new AuthenticationResponse(username, token, userRole, email, phoneNumber);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new JwtUser(user.getUsername(), user.getUserRole(), user.getPassword());
    }

    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not exists"));
    }

}
