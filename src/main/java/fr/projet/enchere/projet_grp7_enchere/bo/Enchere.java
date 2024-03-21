package fr.projet.enchere.projet_grp7_enchere.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class representing an auction.
 */
public class Enchere implements Serializable {
    private long no_enchere;

    @NotNull(message = "{validation.article.notNull}")
    private Article article;

    @NotNull(message = "{validation.utilisateur.notNull}")
    private Utilisateur utilisateur;

    @NotNull(message = "{validation.date_enchere.notNull}")
    private LocalDate date_enchere;

    @Min(value = 0, message = "{validation.montant_enchere.min}")
    private int montant_enchere;


    /**
     * Default constructor for the Enchere class.
     */
    public Enchere() {
    }

    /**
     * Constructor for creating an Enchere object with all parameters.
     *
     * @param article         The article associated with the auction.
     * @param utilisateur     The user associated with the auction.
     * @param date_enchere    The date of the auction.
     * @param montant_enchere The amount of the auction.
     */
    public Enchere(Article article, Utilisateur utilisateur, LocalDate date_enchere, int montant_enchere) {
        this.article = article;
        this.utilisateur = utilisateur;
        this.date_enchere = date_enchere;
        this.montant_enchere = montant_enchere;
    }

    // Getter and setters

    public long getNo_enchere() {
        return no_enchere;
    }

    public void setNo_enchere(long no_enchere) {
        this.no_enchere = no_enchere;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public LocalDate getDate_enchere() {
        return date_enchere;
    }

    public void setDate_enchere(LocalDate date_enchere) {
        this.date_enchere = date_enchere;
    }

    public int getMontant_enchere() {
        return montant_enchere;
    }

    public void setMontant_enchere(int montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    /**
     * Computes a hash code for this auction based on the associated article.
     *
     * @return The hash code value for this auction.
     */
    @Override
    public int hashCode() {
        return Objects.hash(article);
    }

    /**
     * Indicates whether some other object is "equal to" this one based on their unique identifiers.
     *
     * @param obj The reference object with which to compare.
     * @return True if this auction is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Enchere other = (Enchere) obj;
        return no_enchere == other.no_enchere;
    }
}
