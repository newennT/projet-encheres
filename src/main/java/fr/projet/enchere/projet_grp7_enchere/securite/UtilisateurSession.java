package fr.projet.enchere.projet_grp7_enchere.securite;

import fr.projet.enchere.projet_grp7_enchere.bo.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Custom implementation of Spring Security's UserDetails interface for representing authenticated users in the application.
 */
public class UtilisateurSession implements UserDetails {

    private Utilisateur utilisateur;

    /**
     * Default constructor.
     */
    public UtilisateurSession() {
    }

    /**
     * Parameterized constructor to initialize the UtilisateurSession with a given Utilisateur.
     *
     * @param utilisateur The Utilisateur object associated with this session.
     */
    public UtilisateurSession(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * Getter for the associated Utilisateur object.
     *
     * @return The Utilisateur object.
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Setter for the associated Utilisateur object.
     *
     * @param utilisateur The Utilisateur object to set.
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * Returns the authorities (roles) granted to the user. Currently not implemented.
     *
     * @return Always returns null.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO
        return null;
    }

    /**
     * Returns the password for the user.
     *
     * @return Always returns null.
     */
    @Override
    public String getPassword() {
        return utilisateur.getMotDePasse();
    }

    /**
     * Returns the username for the user.
     *
     * @return Always returns null.
     */
    @Override
    public String getUsername() {
        return utilisateur.getPseudo();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or not.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
