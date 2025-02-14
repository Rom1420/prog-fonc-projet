# Projet Prog Fonc

## Objectif
Créer un workflow fonctionnel pour gérer des données d'utilisateurs avec des transformations, des filtres et une exportation vers des fichiers CSV.

## Team
- **Mathias Santos Reis**
- **Romain Abbonato**

## Professeur
- **Françoise Baude**

## Technologies
- **Java** (programmation fonctionnelle)
- **CSV** pour l'import/export
- **Streams** pour transformations de données

## Description du Workflow
1. **Charger les utilisateurs depuis un fichier CSV**
2. **Appliquer des transformations** : filtrage par âge, ville, etc., transformation des noms en majuscules, calcul de la moyenne d'âge.
3. **Fusionner des flux** : combiner des utilisateurs adultes et ceux d'une ville spécifique.
4. **Sauvegarde dans la base de données** : exportation vers des fichiers CSV dans `resources/output`.
5. **Calculer des statistiques** : exemple, proportion des utilisateurs ayant plus de 40 ans.
