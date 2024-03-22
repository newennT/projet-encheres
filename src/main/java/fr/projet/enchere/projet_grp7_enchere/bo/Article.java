package fr.projet.enchere.projet_grp7_enchere.bo;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an article in the system.
 */
public class Article implements Serializable {
    /**
     * These parameters adhere as closely as possible to SIO standards.
     */

    private long no_article;

    @Size(min = 4, max = 70, message = "{validation.nom_article.size}")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ\\- ]+$", message = "{validation.nom_article.pattern}")
    private String nom_article;

    @Size(min = 4, max = 255, message = "{validation.description.size}")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ\\- ]+$", message = "{validation.description.pattern}")
    private String description;

    @FutureOrPresent(message = "{validation.date_debut_encheres.futureOrPresent}")
    private LocalDate date_debut_encheres;

    @Future(message = "{validation.date_fin_encheres.future}")
    private LocalDate date_fin_encheres;

    @Min(value = 0, message = "{validation.prix_initial.min}")
    private int prix_initial;

    @Min(value = 0, message = "{validation.prix_vente.min}")
    private int prix_vente;

    @URL(message = "{validation.article_img.url}")
    private String article_img;

    private Categorie categorie;

    private Utilisateur utilisateur;

    private Retrait retrait;

    /**
     * Default constructor.
     */
    public Article() {
    }

    /**
     * Constructor with name parameter.
     *
     * @param nom_article The name of the article.
     */
    public Article(String nom_article) {
        this.nom_article = nom_article;
    }

    /**
     * Full constructor.
     *
     * @param no_article          The unique identifier for the article.
     * @param nom_article         The name of the article.
     * @param description         The description of the article.
     * @param date_debut_encheres The start date of the auction for the article.
     * @param date_fin_encheres   The end date of the auction for the article.
     * @param prix_initial        The initial price of the article.
     * @param prix_vente          The selling price of the article.
     * @param categorie           The category of the article.
     * @param utilisateur         The user who listed the article.
     */
    public Article(int no_article, String nom_article, String description, LocalDate date_debut_encheres,
                   LocalDate date_fin_encheres, int prix_initial, int prix_vente, String article_img, Retrait retrait,
                   Categorie categorie, Utilisateur utilisateur) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.article_img = article_img;
        this.retrait = retrait;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
    }

    // Getters and setters

    public long getNo_article() {
        return no_article;
    }

    public void setNo_article(long no_article) {
        this.no_article = no_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticle_img() {
        return article_img;
    }

    public void setArticle_img(String article_img) {
        this.article_img = article_img;
    }

    public LocalDate getDate_debut_encheres() {
        return date_debut_encheres;
    }

    public void setDate_debut_encheres(LocalDate date_debut_encheres) {
        this.date_debut_encheres = date_debut_encheres;
    }

    public LocalDate getDate_fin_encheres() {
        return date_fin_encheres;
    }

    public void setDate_fin_encheres(LocalDate date_fin_encheres) {
        this.date_fin_encheres = date_fin_encheres;
    }

    public int getPrix_initial() {
        return prix_initial;
    }

    public void setPrix_initial(int prix_initial) {
        this.prix_initial = prix_initial;
    }

    public int getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(int prix_vente) {
        this.prix_vente = prix_vente;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Retrait getRetrait() {
        return retrait;
    }

    public void setRetrait(Retrait retrait) {
        this.retrait = retrait;
    }

    /**
     * Computes a hash code for this article based on its unique identifier.
     *
     * @return The hash code value for this article.
     */
    @Override
    public int hashCode() {
        return Objects.hash(no_article);
    }

    /**
     * Equals method to compare two instances of the class based on their 'id'.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Article other = (Article) obj;
        return no_article == other.no_article;
    }
}