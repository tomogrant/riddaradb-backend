package com.se.riddaradb.auth.user;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final SessionRegistry sessionRegistry;

    public UserService(PasswordEncoder passwordEncoder,
                       UserMapper userMapper,
                       UserRepository userRepository,
                       SessionRegistry sessionRegistry){
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.sessionRegistry = sessionRegistry;
    }

    public Set<UserDto> getUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toSet());
    }

    public UserDto createUser(UserDto userDto){
        UserEntity userEntity  = new UserEntity();

        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastNames(userDto.getLastNames());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRoles(userDto.getRoles());

        return userMapper.mapToDto(userRepository.save(userEntity));
    }

    public UserDto updateUser(UserDto userDto){
        UserEntity userEntity = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastNames(userDto.getLastNames());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRoles(userDto.getRoles());

        System.out.println("Saving...");
        return userMapper.mapToDto(userRepository.save(userEntity));
    }

    public void deleteUser(String username){

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getRoles().contains(Role.ADMINISTRATOR)){
            throw new IllegalArgumentException("Cannot delete admin");
        }

        sessionRegistry.getAllSessions(username, false)
                .forEach(SessionInformation::expireNow);

        userRepository.deleteByUsername(username);
    }
}
