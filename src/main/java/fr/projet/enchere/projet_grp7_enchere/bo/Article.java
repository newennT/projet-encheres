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

    private String article_img;

    private Categorie categorie;

    private Utilisateur utilisateur;


    public Article() {
    }

    public Article(String nom_article) {
        this.nom_article = nom_article;
    }

    public Article(int no_article, String nom_article, String description, LocalDate date_debut_encheres, LocalDate date_fin_encheres, int prix_initial, int prix_vente, String article_img, Categorie categorie, Utilisateur utilisateur) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.article_img = article_img;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
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