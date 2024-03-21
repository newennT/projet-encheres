package fr.projet.enchere.projet_grp7_enchere.bo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Entity class representing a user in the system.
 */

public class Utilisateur {
    private long noUtilisateur;

    @Size(min = 4, max = 20, message = "{validation.pseudo.size}")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ\\-]+$", message = "{validation.pseudo.pattern}")
    private String pseudo;

    @Size(min = 4, max = 20, message = "{validation.nom.size}")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ\\-]+$", message = "{validation.nom.pattern}")
    private String nom;

    @Size(min = 4, max = 20, message = "{validation.prenom.size}")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ\\-]+$", message = "{validation.prenom.pattern}")
    private String prenom;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{validation.email.pattern}")
    private String email;

    @Pattern(regexp = "^0[1-9]([-. ]?[0-9]{2}){4}$", message = "{validation.telephone.pattern}")
    private String telephone;

    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ\\- ]+$", message = "{validation.rue.pattern}")
    private String rue;

    @Pattern(regexp = "^\\d{5}$", message = "{validation.codePostal.pattern}")
    private String codePostal;

    @Size(min = 4, max = 20, message = "{validation.ville.size}")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ\\-]+$", message = "{validation.ville.pattern}")
    private String ville;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "{validation.motDePasse.pattern}")
    private String motDePasse;

    private int credit;

    private boolean administrateur;

    /**
     * Default constructor.
     */
    public Utilisateur() {
    }

    /**
     * Parameterized constructor to create a new Utilisateur.
     *
     * @param pseudo         The username.
     * @param nom            The last name.
     * @param prenom         The first name.
     * @param email          The email address.
     * @param telephone      The telephone number.
     * @param rue            The street address.
     * @param codePostal     The postal code.
     * @param ville          The city.
     * @param motDePasse     The password.
     * @param credit         The user's credit.
     * @param administrateur Whether the user is an administrator or not.
     */
    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
                       String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }

    /**
     * Parameterized constructor to create a Utilisateur with an existing user ID.
     *
     * @param noUtilisateur  The user ID.
     * @param pseudo         The username.
     * @param nom            The last name.
     * @param prenom         The first name.
     * @param email          The email address.
     * @param telephone      The telephone number.
     * @param rue            The street address.
     * @param codePostal     The postal code.
     * @param ville          The city.
     * @param motDePasse     The password.
     * @param credit         The user's credit.
     * @param administrateur Whether the user is an administrator or not.
     */
    public Utilisateur(long noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
                       String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }

    // Getters and setters

    public long getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(long noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur that)) return false;
        return getNoUtilisateur() == that.getNoUtilisateur() && getCredit() == that.getCredit() &&
                isAdministrateur() == that.isAdministrateur() && Objects.equals(getPseudo(), that.getPseudo()) &&
                Objects.equals(getNom(), that.getNom()) && Objects.equals(getPrenom(), that.getPrenom()) &&
                Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getTelephone(), that.getTelephone()) &&
                Objects.equals(getRue(), that.getRue()) && Objects.equals(getCodePostal(), that.getCodePostal()) &&
                Objects.equals(getVille(), that.getVille()) && Objects.equals(getMotDePasse(), that.getMotDePasse());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNoUtilisateur(), getPseudo(), getNom(), getPrenom(), getEmail(), getTelephone(),
                getRue(), getCodePostal(), getVille(), getMotDePasse(), getCredit(), isAdministrateur());
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "noUtilisateur=" + noUtilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                '}';
    }
}
