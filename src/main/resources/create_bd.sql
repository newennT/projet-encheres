-- Table des catégories de produits
CREATE TABLE CATEGORIES
(
    no_categorie INTEGER AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique de la catégorie
    libelle      VARCHAR(30) NOT NULL                -- Nom de la catégorie
);

-- Table des utilisateurs du système
CREATE TABLE UTILISATEURS
(
    no_utilisateur INTEGER AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique de l'utilisateur
    pseudo         VARCHAR(30)  NOT NULL,              -- Nom d'utilisateur
    nom            VARCHAR(30)  NOT NULL,              -- Nom de famille de l'utilisateur
    prenom         VARCHAR(30)  NOT NULL,              -- Prénom de l'utilisateur
    email          VARCHAR(50)  NOT NULL,              -- Adresse email de l'utilisateur
    telephone      VARCHAR(15),                        -- Numéro de téléphone de l'utilisateur
    rue            VARCHAR(30)  NOT NULL,              -- Rue de résidence de l'utilisateur
    code_postal    VARCHAR(10)  NOT NULL,              -- Code postal de résidence de l'utilisateur
    ville          VARCHAR(50)  NOT NULL,              -- Ville de résidence de l'utilisateur
    mot_de_passe   VARCHAR(255) NOT NULL,              -- Mot de passe de l'utilisateur (crypté)
    credit         INTEGER      NOT NULL,              -- Crédit disponible pour l'utilisateur
    administrateur BIT          NOT NULL               -- Indique si l'utilisateur est administrateur ou non
);

-- Table des articles mis en vente
CREATE TABLE ARTICLES_VENDUS
(
    no_article          INTEGER AUTO_INCREMENT PRIMARY KEY,                                  -- Identifiant unique de l'article
    nom_article         VARCHAR(30)  NOT NULL,                                               -- Nom de l'article
    description         VARCHAR(300) NOT NULL,                                               -- Description de l'article
    date_debut_encheres DATE         NOT NULL,                                               -- Date de début des enchères pour l'article
    date_fin_encheres   DATE         NOT NULL,                                               -- Date de fin des enchères pour l'article
    prix_initial        INTEGER,                                                             -- Prix initial de l'article
    prix_vente          INTEGER,                                                             -- Prix de vente final de l'article
    no_utilisateur      INTEGER      NOT NULL,                                               -- Identifiant de l'utilisateur qui met en vente l'article
    no_categorie        INTEGER      NOT NULL,                                               -- Identifiant de la catégorie de l'article
    article_img         VARCHAR(255),                                                        -- Chemin vers l'image de l'article
    no_retrait          INTEGER,                                                             -- Identifiant du lieu de retrait de l'article
    FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur) ON DELETE CASCADE, -- Clé étrangère reliant à la table UTILISATEURS
    FOREIGN KEY (no_categorie) REFERENCES CATEGORIES (no_categorie),                         -- Clé étrangère reliant à la table CATEGORIES
    FOREIGN KEY (no_retrait) REFERENCES RETRAITS (no_retrait)                                -- Clé étrangère reliant à la table RETRAITS
);

-- Table des lieux de retrait des articles
CREATE TABLE RETRAITS
(
    no_retrait  INTEGER PRIMARY KEY,  -- Identifiant unique du lieu de retrait
    rue         VARCHAR(30) NOT NULL, -- Rue du lieu de retrait
    code_postal VARCHAR(15) NOT NULL, -- Code postal du lieu de retrait
    ville       VARCHAR(30) NOT NULL  -- Ville du lieu de retrait
);

-- Table des enchères sur les articles
CREATE TABLE ENCHERES
(
    no_enchere      INTEGER AUTO_INCREMENT PRIMARY KEY,                                     -- Identifiant unique de l'enchère
    date_enchere    DATETIME NOT NULL,                                                      -- Date et heure de l'enchère
    montant_enchere INTEGER  NOT NULL,                                                      -- Montant de l'enchère
    no_article      INTEGER  NOT NULL,                                                      -- Identifiant de l'article sur lequel porte l'enchère
    no_utilisateur  INTEGER  NOT NULL,                                                      -- Identifiant de l'utilisateur qui enchérit
    FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article),                       -- Clé étrangère reliant à la table ARTICLES_VENDUS
    FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur) ON DELETE CASCADE -- Clé étrangère reliant à la table UTILISATEURS
);
