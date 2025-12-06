package utils;

import entities.*;
import services.*;
import java.time.LocalDate;


public class InitData {

    public static void main(String[] args) {
        try {

            ApprenantService apprenantService = new ApprenantService();
            ProfesseurService professeurService = new ProfesseurService();
            FormationService formationService = new FormationService();
            InscriptionService inscriptionService = new InscriptionService();
            UserService userService = new UserService();
            CoursService coursService = new CoursService();

            System.out.println("1. Création des apprenants...");
            Apprenant marie = new Apprenant("Dupont", "Marie", "marie.dupont@email.com", "0612345678");
            Apprenant pierre = new Apprenant("Martin", "Pierre", "pierre.martin@email.com", "0623456789");
            Apprenant sophie = new Apprenant("Bernard", "Sophie", "sophie.bernard@email.com", "0634567890");
            Apprenant thomas = new Apprenant("Dubois", "Thomas", "thomas.dubois@email.com", "0645678901");

            apprenantService.ajouter(marie);
            apprenantService.ajouter(pierre);
            apprenantService.ajouter(sophie);
            apprenantService.ajouter(thomas);
            System.out.println("   ✓ 4 apprenants créés\n");

            System.out.println("2. Création des professeurs...");
            Professeur rousseau = new Professeur("Rousseau", "Jean", "jean.rousseau@prof.com", "Informatique");
            Professeur moreau = new Professeur("Moreau", "Claire", "claire.moreau@prof.com", "Mathématiques");
            Professeur simon = new Professeur("Simon", "Paul", "paul.simon@prof.com", "Physique");

            professeurService.ajouter(rousseau);
            professeurService.ajouter(moreau);
            professeurService.ajouter(simon);
            System.out.println("   ✓ 3 professeurs créés\n");

            System.out.println("3. Création des utilisateurs...");

            User admin = new User("admin", "admin123", "admin");
            userService.creerUtilisateur(admin);

            User userRousseau = new User("prof.rousseau", "prof123", "professeur");
            userRousseau.setProfesseur(rousseau);
            userService.creerUtilisateur(userRousseau);

            User userMoreau = new User("prof.moreau", "prof123", "professeur");
            userMoreau.setProfesseur(moreau);
            userService.creerUtilisateur(userMoreau);

            User userSimon = new User("prof.simon", "prof123", "professeur");
            userSimon.setProfesseur(simon);
            userService.creerUtilisateur(userSimon);

            User userMarie = new User("marie.dupont", "appr123", "apprenant");
            userMarie.setApprenant(marie);
            userService.creerUtilisateur(userMarie);

            User userPierre = new User("pierre.martin", "appr123", "apprenant");
            userPierre.setApprenant(pierre);
            userService.creerUtilisateur(userPierre);

            User userSophie = new User("sophie.bernard", "appr123", "apprenant");
            userSophie.setApprenant(sophie);
            userService.creerUtilisateur(userSophie);

            User userThomas = new User("thomas.dubois", "appr123", "apprenant");
            userThomas.setApprenant(thomas);
            userService.creerUtilisateur(userThomas);

            System.out.println("   ✓ 8 utilisateurs créés\n");

            System.out.println("4. Création des formations...");
            Formation devWeb = new Formation(
                    "Développement Web Full Stack",
                    300,
                    20,
                    "Formation complète en développement web : HTML, CSS, JavaScript, React, Node.js, bases de données",
                    rousseau
            );

            Formation dataScience = new Formation(
                    "Data Science et Machine Learning",
                    250,
                    15,
                    "Formation en science des données : Python, pandas, scikit-learn, TensorFlow",
                    rousseau
            );

            Formation maths = new Formation(
                    "Mathématiques Appliquées",
                    200,
                    25,
                    "Cours de mathématiques pour l'informatique : algèbre linéaire, probabilités, statistiques",
                    moreau
            );

            Formation physique = new Formation(
                    "Physique Quantique",
                    180,
                    12,
                    "Introduction à la physique quantique et ses applications",
                    simon
            );

            formationService.ajouter(devWeb);
            formationService.ajouter(dataScience);
            formationService.ajouter(maths);
            formationService.ajouter(physique);
            System.out.println("   ✓ 4 formations créées\n");

            // 5. Créer les inscriptions
            System.out.println("5. Création des inscriptions...");
            inscriptionService.inscrire(marie.getId(), devWeb.getId());
            inscriptionService.inscrire(marie.getId(), maths.getId());
            inscriptionService.inscrire(pierre.getId(), devWeb.getId());
            inscriptionService.inscrire(pierre.getId(), dataScience.getId());
            inscriptionService.inscrire(sophie.getId(), dataScience.getId());
            inscriptionService.inscrire(sophie.getId(), maths.getId());
            inscriptionService.inscrire(thomas.getId(), physique.getId());
            System.out.println("   ✓ 7 inscriptions créées\n");

            // 6. Créer les cours
            System.out.println("6. Création des cours...");

            // Cours Dev Web
            Cours htmlCours = new Cours(
                    "Introduction au HTML5",
                    "Bienvenue dans ce premier cours sur le HTML5 !\n\n" +
                            "Le HTML5 est la dernière version du langage de balisage HTML. Il apporte de nombreuses nouvelles fonctionnalités :\n\n" +
                            "1. Nouvelles balises sémantiques : <header>, <nav>, <section>, <article>, <aside>, <footer>\n" +
                            "2. Éléments multimédia : <video> et <audio> pour intégrer des médias sans plugins\n" +
                            "3. Canvas et SVG pour dessiner des graphiques\n" +
                            "4. API JavaScript : Geolocation, Local Storage, Web Workers, WebSocket\n\n" +
                            "Exercice pratique : Créez une page HTML5 avec une structure sémantique complète.",
                    LocalDate.now().toString(),
                    devWeb,
                    rousseau
            );

            Cours cssCours = new Cours(
                    "CSS3 et Flexbox",
                    "Dans ce cours, nous allons explorer CSS3 et le modèle Flexbox.\n\n" +
                            "Flexbox (Flexible Box Layout) :\n" +
                            "- Système de mise en page unidimensionnel\n" +
                            "- Facilite l'alignement des éléments\n" +
                            "- Gestion automatique de l'espace\n\n" +
                            "Propriétés principales : display: flex, justify-content, align-items, flex-direction\n\n" +
                            "Projet pratique : Créez une navbar responsive avec Flexbox.",
                    LocalDate.now().toString(),
                    devWeb,
                    rousseau
            );

            Cours jsCours = new Cours(
                    "JavaScript ES6+",
                    "JavaScript ES6 (ECMAScript 2015) et les versions suivantes ont révolutionné le langage.\n\n" +
                            "Nouvelles fonctionnalités :\n" +
                            "1. let et const\n" +
                            "2. Arrow Functions\n" +
                            "3. Template Literals\n" +
                            "4. Destructuring\n" +
                            "5. Promises et Async/Await\n" +
                            "6. Classes\n\n" +
                            "Exercice : Créez une application todo avec ES6+.",
                    LocalDate.now().toString(),
                    devWeb,
                    rousseau
            );

            // Cours Data Science
            Cours pythonCours = new Cours(
                    "Introduction à Python",
                    "Python est le langage de référence pour la Data Science.\n\n" +
                            "Concepts fondamentaux :\n" +
                            "1. Types de données : nombres, textes, listes, dictionnaires\n" +
                            "2. Structures de contrôle : if/else, for, while\n" +
                            "3. Fonctions et lambda functions\n" +
                            "4. Compréhensions de liste\n\n" +
                            "Exercice : Créez un programme d'analyse de données simple.",
                    LocalDate.now().toString(),
                    dataScience,
                    rousseau
            );

            Cours pandasCours = new Cours(
                    "Pandas et NumPy",
                    "Pandas et NumPy sont les bibliothèques essentielles pour la manipulation de données.\n\n" +
                            "NumPy - Calcul numérique :\n" +
                            "- Création d'arrays\n" +
                            "- Opérations mathématiques\n" +
                            "- Statistiques\n\n" +
                            "Pandas - Manipulation de données :\n" +
                            "- DataFrames\n" +
                            "- Lecture/écriture de fichiers\n" +
                            "- Nettoyage et transformation\n" +
                            "- Agrégation et groupby\n\n" +
                            "Projet : Analysez un dataset réel avec Pandas.",
                    LocalDate.now().toString(),
                    dataScience,
                    rousseau
            );

            // Cours Maths
            Cours algebreCours = new Cours(
                    "Algèbre Linéaire",
                    "L'algèbre linéaire est fondamentale en informatique et data science.\n\n" +
                            "Concepts clés :\n" +
                            "1. Vecteurs et matrices\n" +
                            "2. Systèmes d'équations linéaires\n" +
                            "3. Espaces vectoriels\n" +
                            "4. Transformations linéaires\n\n" +
                            "Applications : Graphisme 3D, Machine Learning, Traitement d'images\n\n" +
                            "Exercices pratiques fournis.",
                    LocalDate.now().toString(),
                    maths,
                    moreau
            );

            Cours statsCours = new Cours(
                    "Probabilités et Statistiques",
                    "Les probabilités et statistiques sont essentielles pour l'analyse de données.\n\n" +
                            "Programme :\n" +
                            "1. Probabilités de base et théorème de Bayes\n" +
                            "2. Distributions (normale, binomiale, Poisson)\n" +
                            "3. Statistiques descriptives (moyenne, variance, corrélation)\n" +
                            "4. Inférence statistique (tests d'hypothèses, p-value)\n" +
                            "5. Régression linéaire\n\n" +
                            "Applications : A/B Testing, Analyse prédictive, Détection d'anomalies\n\n" +
                            "Projet : Analyse statistique d'un dataset.",
                    LocalDate.now().toString(),
                    maths,
                    moreau
            );

            // Cours Physique
            Cours quantiqueCours = new Cours(
                    "Introduction à la Physique Quantique",
                    "La physique quantique décrit le comportement de la matière à l'échelle atomique.\n\n" +
                            "Concepts fondamentaux :\n" +
                            "1. Dualité onde-corpuscule\n" +
                            "2. Principe d'incertitude de Heisenberg\n" +
                            "3. Fonction d'onde et équation de Schrödinger\n" +
                            "4. Principe de superposition\n" +
                            "5. Intrication quantique\n\n" +
                            "Applications modernes :\n" +
                            "- Ordinateurs quantiques\n" +
                            "- Cryptographie quantique\n" +
                            "- Imagerie médicale (IRM)\n" +
                            "- Technologies des semi-conducteurs",
                    LocalDate.now().toString(),
                    physique,
                    simon
            );

            coursService.ajouter(htmlCours);
            coursService.ajouter(cssCours);
            coursService.ajouter(jsCours);
            coursService.ajouter(pythonCours);
            coursService.ajouter(pandasCours);
            coursService.ajouter(algebreCours);
            coursService.ajouter(statsCours);
            coursService.ajouter(quantiqueCours);

            System.out.println("   ✓ 8 cours créés\n");

            System.out.println("=== Initialisation terminée avec succès ! ===\n");

            System.out.println("Comptes créés :");
            System.out.println("Admin        : admin / admin123");
            System.out.println("Professeurs  : prof.rousseau, prof.moreau, prof.simon / prof123");
            System.out.println("Apprenants   : marie.dupont, pierre.martin, sophie.bernard, thomas.dubois / appr123");

        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation : " + e.getMessage());
            e.printStackTrace();
        }
    }
}