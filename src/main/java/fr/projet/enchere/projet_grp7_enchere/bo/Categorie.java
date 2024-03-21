package fr.projet.enchere.projet_grp7_enchere.bo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a category in the system.
 */
public class Categorie implements Serializable {
    private long no_categorie;
    private String libelle;

    /**
     * Default constructor.
     */
    public Categorie() {
    }

    /**
     * Constructor with label parameter.
     *
     * @param libelle The label of the category.
     */
    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Full constructor.
     *
     * @param no_categorie The unique identifier of the category.
     * @param libelle      The label of the category.
     */
    public Categorie(int no_categorie, String libelle) {
        this.no_categorie = no_categorie;
        this.libelle = libelle;
    }

    // Getter and setters

    public long getNo_categorie() {
        return no_categorie;
    }

    public void setNo_categorie(long no_categorie) {
        this.no_categorie = no_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Computes a hash code for this category based on its unique identifier.
     *
     * @return The hash code value for this category.
     */
    @Override
    public int hashCode() {
        return Objects.hash(no_categorie);
    }

    /**
     * Indicates whether some other object is "equal to" this one based on their unique identifiers.
     *
     * @param obj The reference object with which to compare.
     * @return True if this category is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categorie other = (Categorie) obj;
        return no_categorie == other.no_categorie;
    }
}
