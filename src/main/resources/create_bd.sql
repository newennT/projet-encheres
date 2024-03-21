CREATE TABLE CATEGORIES (
                            no_categorie INTEGER AUTO_INCREMENT PRIMARY KEY,
                            libelle VARCHAR(30) NOT NULL
);

CREATE TABLE UTILISATEURS (
                              no_utilisateur INTEGER AUTO_INCREMENT PRIMARY KEY,
                              pseudo VARCHAR(30) NOT NULL,
                              nom VARCHAR(30) NOT NULL,
                              prenom VARCHAR(30) NOT NULL,
                              email VARCHAR(50) NOT NULL,
                              telephone VARCHAR(15),
                              rue VARCHAR(30) NOT NULL,
                              code_postal VARCHAR(10) NOT NULL,
                              ville VARCHAR(50) NOT NULL,
                              mot_de_passe VARCHAR(255) NOT NULL,
                              credit INTEGER NOT NULL,
                              administrateur BIT NOT NULL
);

CREATE TABLE ARTICLES_VENDUS (
                                 no_article INTEGER AUTO_INCREMENT PRIMARY KEY,
                                 nom_article VARCHAR(30) NOT NULL,
                                 description VARCHAR(300) NOT NULL,
                                 date_debut_encheres DATE NOT NULL,
                                 date_fin_encheres DATE NOT NULL,
                                 prix_initial INTEGER,
                                 prix_vente INTEGER,
                                 no_utilisateur INTEGER NOT NULL,
                                 no_categorie INTEGER NOT NULL,
                                 article_img VARCHAR(255),
                                 no_retrait INTEGER,
                                 FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS(no_utilisateur),
                                 FOREIGN KEY (no_categorie) REFERENCES CATEGORIES(no_categorie),
                                 FOREIGN KEY (no_retrait) REFERENCES RETRAITS(no_retrait)
);

CREATE TABLE RETRAITS (
                          no_retrait INTEGER PRIMARY KEY,
                          rue VARCHAR(30) NOT NULL,
                          code_postal VARCHAR(15) NOT NULL,
                          ville VARCHAR(30) NOT NULL
);

CREATE TABLE ENCHERES (
                          no_enchere INTEGER AUTO_INCREMENT PRIMARY KEY,
                          date_enchere DATETIME NOT NULL,
                          montant_enchere INTEGER NOT NULL,
                          no_article INTEGER NOT NULL,
                          no_utilisateur INTEGER NOT NULL,
                          FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS(no_article),
                          FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS(no_utilisateur)
);
