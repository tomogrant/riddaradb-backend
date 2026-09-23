package com.se.riddaradb.auth.security;

import com.se.riddaradb.auth.user.Role;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static org.springframework.security.core.userdetails.User.builder;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        final String adminPattern = "/admin/**";
        final String sagaPattern = "/sagas/**";
        final String sagaVersionPattern = "/sagaversions/**";
        final String bibPattern = "/bib/**";
        final String motifPattern = "/motifs/**";
        final String msPattern = "/ms/**";
        final String msRepoPattern = "/msrepository/**";
        final String characterPattern = "/characters/**";
        final String locationPattern = "/locations/**";

        httpSecurity
                .csrf(csrf ->
                        csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                .authorizeHttpRequests(auth -> auth

                        //Authentication; me and log out only if authenticated; anyone can log in and get the CSRF
                        .requestMatchers("/auth/me", "/auth/logout").authenticated()
                        .requestMatchers("/auth/**").permitAll()

                        //Admin actions
                        .requestMatchers(adminPattern).hasRole(Role.ADMINISTRATOR.name())

                        //Sagas
                        .requestMatchers(HttpMethod.GET, sagaPattern).permitAll()
                        .requestMatchers(HttpMethod.DELETE, sagaPattern).hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(sagaPattern).authenticated()

                        //Saga versions
                        .requestMatchers(HttpMethod.GET, sagaVersionPattern).permitAll()
                        .requestMatchers(HttpMethod.DELETE, sagaVersionPattern).hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(sagaVersionPattern).authenticated()

                        //Bibliography entries
                        .requestMatchers(HttpMethod.GET, bibPattern).permitAll()
                        .requestMatchers(HttpMethod.DELETE, bibPattern).hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(bibPattern).authenticated()

                        //Motifs
                        .requestMatchers(HttpMethod.GET, motifPattern).permitAll()
                        .requestMatchers(HttpMethod.DELETE, motifPattern).hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(motifPattern).authenticated()

                        //Manuscripts
                        .requestMatchers(HttpMethod.GET, msPattern).permitAll()
                        .requestMatchers(HttpMethod.DELETE, msPattern).hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(msPattern).authenticated()

                        //Manuscript repositories
                        .requestMatchers(HttpMethod.GET, msRepoPattern).permitAll()
                        .requestMatchers(HttpMethod.DELETE, msRepoPattern).hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(msRepoPattern).authenticated()

                        //Characters
                        .requestMatchers(HttpMethod.GET, characterPattern).permitAll()
                        .requestMatchers(HttpMethod.DELETE, characterPattern).hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(characterPattern).authenticated()

                        //Locations
                        .requestMatchers(HttpMethod.GET, locationPattern).permitAll()
                        .requestMatchers(HttpMethod.DELETE, locationPattern).hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(locationPattern).authenticated()
                )
                //No form as there will be an Angular form
                .formLogin(AbstractHttpConfigurer::disable)
                //Log out endpoint
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication((true))
                        .logoutSuccessHandler(
                                (request, response, authentication) ->
                                response.setStatus(HttpServletResponse.SC_NO_CONTENT))
                );

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
