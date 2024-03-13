package fr.projet.enchere.projet_grp7_enchere.bo;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Article implements Serializable {

    private long no_article;

    private String nom_article;
    private String description;
    private LocalDate date_debut_encheres;
    private LocalDate date_fin_encheres;

    private int prix_initial;
    private int prix_vente;

    private Categorie no_categorie;

    private Utilisateur no_utilisateur;


    public Article() {
    }

    public Article(String nom_article) {
        this.nom_article = nom_article;
    }

    public Article(int no_article, String nom_article, String description, LocalDate date_debut_encheres, LocalDate date_fin_encheres, int prix_initial, int prix_vente, Categorie no_categorie) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.no_categorie = no_categorie;
    }

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

    public Categorie getNo_categorie() {
        return no_categorie;
    }

    public void setNo_categorie(Categorie no_categorie) {
        this.no_categorie = no_categorie;
    }

    public Utilisateur getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(Utilisateur no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    //Equals et hashCode pour comparer 2 instances de la classe selon leur 'id'
    @Override
    public int hashCode() {
        return Objects.hash(no_article);
    }

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
