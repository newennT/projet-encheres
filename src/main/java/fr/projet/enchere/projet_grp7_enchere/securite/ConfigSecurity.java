package fr.projet.enchere.projet_grp7_enchere.securite;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

/**
 * Configuration class for security settings.
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity {

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
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/static").permitAll();
            auth.requestMatchers("/inscription").permitAll();

            auth.requestMatchers("/css/*").permitAll()
                    .requestMatchers("/images/*").permitAll()
                    .requestMatchers("/javascript/*").permitAll()
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated();
        });

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
                    if(authentication!=null) {
                        UtilisateurSession session = (UtilisateurSession) authentication.getPrincipal();
                        request.getSession().setAttribute("currentUser", session.getUtilisateur());
                    }
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            });
        });

        // /logout --> invalidate session and clear security context
        http.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll());

        return http.build();
    }
}