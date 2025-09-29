package com.example.rbac.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rbac.entity.UserInfo;
import com.example.rbac.repo.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    // Method to load user details by username (email)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Fetch user from the database by email (username)
//        Optional<UserInfo> userInfo = repository.findByEmail(username);
//        
//        if (userInfo.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with email: " + username);
//        }
//        
//        // Convert UserInfo to UserDetails (UserInfoDetails)
//        UserInfo user = userInfo.get();
//        return new User(user.getEmail(), user.getPassword(), user.getRoles());
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(user -> new User(
                        user.getEmail(),
                        user.getPassword(),
                        user.getRoles() // now returns List<? extends GrantedAuthority>
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
    // Add any additional methods for registering or managing users
    public String addUser(UserInfo userInfo) {
        // Encrypt password before saving
        userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
        repository.save(userInfo);
        return "User added successfully!";
    }
}
