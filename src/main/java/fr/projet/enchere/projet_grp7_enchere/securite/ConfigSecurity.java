package fr.projet.enchere.projet_grp7_enchere.securite;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Configuration class for security settings.
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    private final UtilisateurSessionService utilisateurSessionService;

    @Autowired
    public ConfigSecurity(UtilisateurSessionService utilisateurSessionService) {
        this.utilisateurSessionService = utilisateurSessionService;
    }

    /**
     * Configures the security filter chain.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Authorization rules - Allow access without authentication
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/").permitAll()
                    .requestMatchers("/encheres").permitAll()
                    .requestMatchers("/static").permitAll()
                    .requestMatchers("/inscription").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/login-error").permitAll();

            //User - isAdmin == False
            auth.requestMatchers("/logout").hasAnyRole("UTILISATEUR", "ADMINISTRATEUR")
                    .requestMatchers("/encheres/details").hasAnyRole("UTILISATEUR", "ADMINISTRATEUR")
                    .requestMatchers("/encheres/ajout-article").hasAnyRole("UTILISATEUR", "ADMINISTRATEUR")
                    .requestMatchers("/utilisateur/details").hasAnyRole("UTILISATEUR", "ADMINISTRATEUR")
                    .requestMatchers("/utilisateur/my-profil").hasAnyRole("UTILISATEUR", "ADMINISTRATEUR")
                    .requestMatchers("/utilisateur/delete-my-account").hasAnyRole("UTILISATEUR", "ADMINISTRATEUR");

            //Admin user - isAdmin == True
            auth.requestMatchers("/administrator/delete-user").hasRole("ADMINISTRATEUR")
                    .requestMatchers("/administrator/categories").hasRole("ADMINISTRATEUR");

            auth.requestMatchers("/css/*").permitAll()
                    .requestMatchers("/images/*").permitAll()
                    .requestMatchers("/javascript/*").permitAll()
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated();
        });

        // Redirection personnalisée en cas d'accès refusé
        http.exceptionHandling(e ->
                e.accessDeniedPage("/"));

        // Customize the login form
        http.formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/").permitAll();
            form.failureUrl("/login-error");

            // Define the behavior upon successful login
            form.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                    Authentication authentication)
                        throws IOException, ServletException {

                    // Store the authenticated user in a session variable
                    if (authentication != null) {
                        UtilisateurSession session = (UtilisateurSession) authentication.getPrincipal();

                        String role = session.getUtilisateur().isAdministrateur() ? "ADMINISTRATEUR" : "UTILISATEUR";

                        Collection<GrantedAuthority> authorities = new ArrayList<>();
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                        Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(
                                authentication.getPrincipal(), authentication.getCredentials(), authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

                        request.getSession().setAttribute("currentUser", session.getUtilisateur());
                    }
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            });
        });

        // Configures Remember-Me authentication.
        http.rememberMe(rememberMe -> rememberMe
                .userDetailsService(utilisateurSessionService) // Sets the user details service for Remember-Me authentication.
                .key("secretKey") // Sets the key used for Remember-Me token generation and validation.
                .tokenValiditySeconds(604800)); // Sets the validity period (in seconds) for Remember-Me tokens.

        // Configures session management.
        http.sessionManagement(session -> session
                .invalidSessionUrl("/")); // Sets the URL to redirect to if an invalid session is detected.

        // /logout --> invalidate session and clear security context
        http.logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll());

        return http.build();
    }
}
