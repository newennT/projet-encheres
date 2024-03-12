package fr.projet.enchere.projet_grp7_enchere.bo;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Enchere implements Serializable {
    private long no_enchere;

    Article no_article;
    Utilisateur no_utilisateur;

    private LocalDate date_enchere;
    private int montant_enchere;

    public Enchere() {
    }

    public Enchere(int no_enchere, Article no_article, Utilisateur no_utilisateur, LocalDate date_enchere, int montant_enchere) {
        this.no_enchere = no_enchere;
        this.no_article = no_article;
        this.no_utilisateur = no_utilisateur;
        this.date_enchere = date_enchere;
        this.montant_enchere = montant_enchere;
    }

    public long getNo_enchere() {
        return no_enchere;
    }

    public void setNo_enchere(long no_enchere) {
        this.no_enchere = no_enchere;
    }

    public Article getNo_article() {
        return no_article;
    }

    public void setNo_article(Article no_article) {
        this.no_article = no_article;
    }

    public Utilisateur getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(Utilisateur no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
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
        Enchere other = (Enchere) obj;
        return no_enchere == other.no_enchere;
    }


}
