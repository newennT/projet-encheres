package fr.projet.enchere.projet_grp7_enchere.bo;


import java.io.Serializable;

public class Retrait implements Serializable {
   Article no_article;

   private String rue;
   private String code_postal;
   private String ville;

   public Retrait() {
   }

   public Retrait(Article no_article, String rue, String code_postal, String ville) {
      this.no_article = no_article;
      this.rue = rue;
      this.code_postal = code_postal;
      this.ville = ville;
   }

   public Article getNo_article() {
      return no_article;
   }

   public void setNo_article(Article no_article) {
      this.no_article = no_article;
   }

   public String getRue() {
      return rue;
   }

   public void setRue(String rue) {
      this.rue = rue;
   }

   public String getCode_postal() {
      return code_postal;
   }

   public void setCode_postal(String code_postal) {
      this.code_postal = code_postal;
   }

   public String getVille() {
      return ville;
   }

   public void setVille(String ville) {
      this.ville = ville;
   }
}
