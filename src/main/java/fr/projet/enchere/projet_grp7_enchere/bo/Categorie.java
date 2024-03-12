package fr.projet.enchere.projet_grp7_enchere.bo;


import java.io.Serializable;
import java.util.Objects;


public class Categorie implements Serializable {
    private long no_categorie;

    private String libelle;

    //Default Constructor

    public Categorie(){

    }

    // Constructeur avec paramètre

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

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

    //Equals et hashCode pour comparer 2 instances de la classe selon leur 'id'
    @Override
    public int hashCode() {
        return Objects.hash(no_categorie);
    }

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
