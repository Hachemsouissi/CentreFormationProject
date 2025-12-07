Vue d'ensembleCentre:
de Formation est une application web Java EE permettant la gestion complète d'un centre de formation. 
Elle offre trois espaces distincts : administrateur, professeur et apprenant, chacun avec des fonctionnalités adaptées à son rôle.Architecture de l'applicationTechnologies utiliséesBackend :

Java 17
Jakarta EE 6.0 (Servlet API, JSP, JSTL)
Hibernate ORM 6.4.0 (Couche de persistance)
MySQL 8.0 (Base de données)
Maven (Gestion des dépendances)

Frontend :

JSP (JavaServer Pages)
CSS3 (Styles inline)
JavaScript (Gestion dynamique des formulaires)
Serveur d'application :

Apache Tomcat 10.1.49
Structure du projetCentreFormationProject/
├── src/main/java/
│   ├── controllers/       # Servlets de contrôle
│   ├── dao/              # Data Access Objects
│   ├── entities/         # Entités JPA
│   ├── services/         # Logique métier
│   ├── filters/          # Filtres de sécurité
│   └── utils/            # Utilitaires (HibernateUtil)
├── src/main/resources/
│   └── hibernate.cfg.xml # Configuration Hibernate
├── src/main/webapp/
│   └── WEB-INF/
│       ├── views/        # Pages JSP
│       ├── error/        # Pages d'erreur
│       └── web.xml       # Configuration web
└── pom.xml               # Configuration MavenModèle de donnéesEntités principalesUser

Gestion de l'authentification
Liaison avec Apprenant ou Professeur selon le rôle
Mot de passe hashé avec SHA-256
Apprenant

Informations personnelles (nom, prénom, email, téléphone)
Relation avec les inscriptions
Professeur

Informations personnelles et spécialité
Relation avec les formations et cours
Formation

Titre, durée, capacité, description
Professeur responsable
Inscription

Liaison entre Apprenant et Formation
Date d'inscription
Vérification de capacité et doublons
Cours

Contenu pédagogique
Statut de visibilité (publié/brouillon)
Appartenance à une formation
Architecture en couchesCouche DAO (Data Access Object)
Les DAO gèrent l'accès aux données avec Hibernate :

Opérations CRUD standard (Create, Read, Update, Delete)
Requêtes HQL personnalisées
Gestion des transactions
Couche Service
Les services implémentent la logique métier :

Validation des données
Règles métier (capacité des formations, doublons)
Orchestration des opérations DAO
Couche Controller (Servlets)
Les servlets gèrent les requêtes HTTP :

Routage basé sur le paramètre "action"
Préparation des données pour les vues
Redirection selon le résultat des opérations
Couche Vue (JSP)
Les JSP affichent les données :

Utilisation de JSTL pour la logique d'affichage
Formulaires HTML pour la saisie
Styles CSS intégrés
Fonctionnalités par rôleAdministrateur

Tableau de bord avec statistiques globales
Gestion des apprenants (CRUD complet)
Gestion des professeurs (CRUD complet)
Gestion des formations (CRUD complet)
Gestion des inscriptions
Gestion des utilisateurs et comptes
Professeur

Tableau de bord personnel
Visualisation de ses formations
Gestion de ses cours (création, modification, suppression)
Publication/masquage des cours
Consultation de la liste des apprenants inscrits
Apprenant

Tableau de bord personnel
Visualisation de ses inscriptions
Accès aux cours publiés de ses formations
Consultation du contenu pédagogique
SécuritéAuthentification

Hashage des mots de passe avec SHA-256
Session utilisateur après connexion réussie
Stockage du rôle en session
Autorisation

Filtre AuthFilter vérifiant l'authentification
Vérification du rôle pour chaque espace
Redirection automatique vers la page de connexion si non authentifié
Protection des données

Validation côté serveur de toutes les entrées
Vérification des appartenances (professeur/cours, apprenant/formation)
Gestion des erreurs avec messages appropriés
Configuration HibernateL'application utilise Hibernate en mode "update" :

Création automatique des tables si inexistantes
Mise à jour du schéma sans perte de données
Mapping objet-relationnel via annotations JPA
Dialecte : MySQL8Dialect
Stratégie de génération des ID : IDENTITY
Pool de connexions : Gestion automatique par HibernateFlux de données
L'utilisateur soumet une requête HTTP
Le filtre AuthFilter vérifie l'authentification
Le servlet correspondant traite la requête
Le service applique la logique métier
Le DAO interagit avec la base de données via Hibernate
Le résultat est renvoyé au servlet
Le servlet prépare les données pour la vue
La JSP affiche le résultat à l'utilisateur
Gestion des erreurs
Pages d'erreur personnalisées (404, 500)
Messages d'erreur contextuels dans les formulaires
Gestion des exceptions avec try-catch
Rollback automatique des transactions en cas d'erreur
Points techniques importantsHibernateUtil
Singleton gérant la SessionFactory Hibernate, garantissant une seule instance pour toute l'application.Pattern DAO
Séparation claire entre la logique d'accès aux données et la logique métier.MVC
Architecture Modèle-Vue-Contrôleur respectée :

Modèle : Entités JPA
Vue : Pages JSP
Contrôleur : Servlets
Validation
Validation à plusieurs niveaux :

Contraintes HTML5 (required, email)
Validation côté serveur dans les services
Contraintes JPA (@Column nullable, unique)
Base de donnéesNom de la base : centreformationTables créées automatiquement :

Users
Apprenants
Professeurs
Formations
Inscriptions
Cours
Relations :

One-to-One : User - Apprenant/Professeur
Many-to-One : Formation - Professeur
Many-to-One : Cours - Formation/Professeur
Many-to-One : Inscription - Apprenant/Formation
