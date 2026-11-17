# TP Services Web RESTful : Factoriel

## Objectif

1. Créer un service web RESTful avec **JAX-RS**, appelé **Factoriel**, offrant les opérations suivantes :
   - le factoriel d'un entier : `n!`
   - la somme des factoriels de 3 entiers : `x! + y! + z!`
   - le factoriel de la somme de 3 entiers : `(x + y + z)!`
   - le produit des factoriels de 3 entiers : `x! * y! * z!`
   - le factoriel du produit de 3 entiers : `(x * y * z)!`
2. Créer une application client permettant de consommer ce service.

## Structure du projet

```
Factoriel/            -> Service web (WAR déployé sur le serveur)
Client-Factoriel/     -> Application client console
└── src/main/java/tn/tp/client/ClientFactoriel.java
```

## Technologies

- Java 8
- JAX-RS (API REST)
- Serveur d'applications / conteneur de servlets (Tomcat, WildFly, Payara...)
- Client JAX-RS (Jersey Client ou RESTEasy Client)
- Eclipse IDE, Maven

## Partie 1 : Service web

**URL de base :** `http://localhost:8080/Factoriel-1.0/api/factoriel`

| Opération            | Méthode | Chemin                                   | Exemple                                  |
|----------------------|---------|------------------------------------------|------------------------------------------|
| `n!`                 | GET     | `/{n}`                                   | `/5` → `120`                             |
| `x! + y! + z!`       | GET     | `/somme-factoriels?x=&y=&z=`             | `?x=3&y=4&z=5` → `150`                   |
| `(x + y + z)!`       | GET     | `/factoriel-somme?x=&y=&z=`              | `?x=1&y=2&z=2` → `120`                   |
| `x! * y! * z!`       | GET     | `/produit-factoriels?x=&y=&z=`           | `?x=2&y=3&z=4` → `288`                   |
| `(x * y * z)!`       | GET     | `/factoriel-produit?x=&y=&z=`            | `?x=1&y=2&z=3` → `720`                   |

Les réponses sont retournées en `text/plain`. En cas d'erreur (paramètre invalide, entier négatif...), le service renvoie un code HTTP différent de 200 avec un message.

### Déploiement

1. Importer le projet `Factoriel` dans Eclipse.
2. Lancer *Maven → Clean Install* pour générer `Factoriel-1.0.war`.
3. Déployer le WAR sur le serveur (ou *Run As → Run on Server*).
4. Tester dans le navigateur : `http://localhost:8080/Factoriel-1.0/api/factoriel/5`

## Partie 2 : Application client

Client console (classe `tn.tp.client.ClientFactoriel`) qui consomme le service via l'API client JAX-RS (`Client`, `WebTarget`, `Response`).

### Dépendances (pom.xml)

```xml
<dependency>
    <groupId>org.glassfish.jersey.core</groupId>
    <artifactId>jersey-client</artifactId>
    <version>2.35</version>
</dependency>
<dependency>
    <groupId>org.glassfish.jersey.inject</groupId>
    <artifactId>jersey-hk2</artifactId>
    <version>2.35</version>
</dependency>
```

### Exécution

1. S'assurer que le service est déployé et accessible.
2. Lancer `ClientFactoriel` (*Run As → Java Application*).
3. Choisir une option dans le menu :

```
===== Client Factoriel =====
1. n!
2. x! + y! + z!
3. (x + y + z)!
4. x! * y! * z!
5. (x * y * z)!
0. Quitter
```

## Remarques

- Le code est compatible Java 8 
- Les factoriels croissent très vite : prévoir un type adapté (`long` ou `BigInteger`) côté serveur pour éviter les dépassements.
- Le client et le service sont deux projets distincts.

## Auteur

Alae Balbali, ISI Ariana
