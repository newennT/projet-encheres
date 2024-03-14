package fr.projet.enchere.projet_grp7_enchere.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class.
 */
@Configuration
public class Conf {

    /**
     * Defines the password encoder bean.
     *
     * @return A BCryptPasswordEncoder for password hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the message source bean.
     *
     * @return A ResourceBundleMessageSource for loading messages from properties files.
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages_fr");
        return messageSource;
    }
}
