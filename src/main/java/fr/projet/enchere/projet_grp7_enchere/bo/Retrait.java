package fr.projet.enchere.projet_grp7_enchere.bo;


import java.io.Serializable;

public class Retrait implements Serializable {
   private long no_retrait;

   private String rue;
   private String code_postal;
   private String ville;

   public Retrait() {
   }

   public Retrait(long no_retrait, String rue, String code_postal, String ville) {
      this.no_retrait = no_retrait;
      this.rue = rue;
      this.code_postal = code_postal;
      this.ville = ville;
   }

   public Retrait(String rue, String code_postal, String ville) {
      this.rue = rue;
      this.code_postal = code_postal;
      this.ville = ville;
   }

   public long getNo_retrait() {
      return no_retrait;
   }

   public void setNo_retrait(long no_retrait) {
      this.no_retrait = no_retrait;
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
