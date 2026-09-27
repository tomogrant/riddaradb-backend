package com.se.riddaradb.auth.security;

import com.se.riddaradb.auth.user.UserEntity;
import com.se.riddaradb.auth.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {

    private final UserRepository userRepository;

    public AuthenticationController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("csrf")
    public CsrfToken csrf(CsrfToken csrfToken){
        return csrfToken;
    }

//    @PostMapping("login")
//    public ResponseEntity<?> login(
//            //Record containing credentials supplied by user
//            @RequestBody LoginRequest loginRequest,
//            //Gives controller access to underlying HTTP request
//            HttpServletRequest httpServletRequest){
//
//        //Creates empty security container
//        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//
//        //Searches for user with UserDetailsService and decrypts password.
//        //If there's a match, return an athenticated Authentication object;
//        //if not, throw an exception.
//        Authentication authentication = authenticationManager.authenticate(
//                //Authentication token built from credentials supplied by user
//                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
//        );
//
//        //Assigns authentication object to the security container
//        securityContext.setAuthentication(authentication);
//
//        //Gets the HTTP session associated with this request. true =
//        //if there isn't currently a session, create one.
//        HttpSession httpSession = httpServletRequest.getSession(true);
//
//        //Persists the security context container to the session.
//        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
//
//        return ResponseEntity.ok(new LoginResponse(authentication.getName()));
//    }

    @GetMapping("me")
    public ResponseEntity<UserResponse> me (Authentication authentication){

        UserEntity user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database"));

        return ResponseEntity.ok(new UserResponse(
                authentication.getName(),
                user.getFirstName(),
                user.getLastNames(),
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()));
    }
}
