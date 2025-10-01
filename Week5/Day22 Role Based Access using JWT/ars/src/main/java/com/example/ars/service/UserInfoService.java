package com.example.ars.service;

import com.example.ars.model.UserInfo;
import com.example.ars.repo.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;

    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    /**
     * Load user by email (username)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByEmail(username);

        UserInfo user = userInfo.orElseThrow(() -> 
            new UsernameNotFoundException("User not found with email: " + username));

        // Handle multiple roles (comma-separated in DB) with null check
        String roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = roles != null && !roles.trim().isEmpty() 
            ? Arrays.stream(roles.split(","))
                .map(String::trim)
                .filter(role -> !role.isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
            : List.of(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    /**
     * Add new user with encrypted password
     */
    public String addUser(UserInfo userInfo) {
        try {
            if (userInfo.getPassword() == null || userInfo.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty");
            }
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            return "User added successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Failed to add user: " + e.getMessage(), e);
        }
    }
}
