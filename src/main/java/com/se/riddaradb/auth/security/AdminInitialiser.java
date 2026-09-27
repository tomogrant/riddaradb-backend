package com.se.riddaradb.auth.security;

import com.se.riddaradb.auth.user.Role;
import com.se.riddaradb.auth.user.UserEntity;
import com.se.riddaradb.auth.user.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdminInitialiser implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Make these secret in production
    private String username = "admin";
    private String password = "password";

    public AdminInitialiser(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) throws Exception {

        if (userRepository.findByUsername(username).isPresent()){
            return;
        }

        UserEntity admin = new UserEntity();

        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRoles(Set.of(Role.ADMINISTRATOR));

        admin.setFirstName("Tom");
        admin.setLastNames("Grant");
        admin.setEmail("thomasolivergrant@gmail.com");

        System.out.println("Creating admin...");
        userRepository.save(admin);
    }
}
