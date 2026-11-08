# TP 2 : Inversion de Contrôle (IoC) et Injection de Dépendances avec Spring Framework

**Auteur** : [Mouad Agdouz](https://github.com/agdouz)  
**Formation** : Architecture Microservices : Conception, Déploiement et Orchestration — MLIAEdu  
**Module** : Spring Core, Inversion of Control (IoC), Dependency Injection (DI) & Profils

---

## 🎯 Objectifs Pédagogiques

1. **Maîtriser le conteneur léger Spring IoC (`ApplicationContext`)** :
   - Découverte automatique des composants via `@ComponentScan` et détection des stéréotypes `@Component`.
   - Injection de dépendances automatique avec `@Autowired`.
   - Résolution d'ambiguïté entre multiples implémentations avec `@Qualifier`.
2. **Configuration moderne par annotations (`@Configuration`)** :
   - Utilisation de `AnnotationConfigApplicationContext` sans fichier XML.
   - Gestion des environnements et commutation dynamique avec `@Profile("dev")` / `@Profile("prod")`.
3. **Configuration déclarative XML (`applicationContext.xml`)** :
   - Comprendre le fonctionnement historique avec `ClassPathXmlApplicationContext`.
   - Définition des balises `<bean>`, injection par propriété `<property>` et référence `ref`.
4. **Validation par les tests unitaires** :
   - Tests indépendants et tests d'intégration du conteneur Spring avec JUnit 5.

---

## 🏗️ Structure du Projet

```text
TP2-Spring-IoC-Injection-Dependances/
├── pom.xml                                     # Configuration Maven (Java 17, Spring 6.1.2, JUnit 5)
├── README.md                                   # Documentation exhaustive du TP
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── dao/
    │   │   │   ├── IDao.java                   # Contrat de persistance
    │   │   │   ├── DaoImpl.java                # @Component("dao") @Profile("prod") -> valeur 100.0
    │   │   │   └── DaoImpl2.java               # @Component("dao2") @Profile("dev") -> valeur 150.0
    │   │   ├── metier/
    │   │   │   ├── IMetier.java                # Contrat de la logique métier
    │   │   │   └── MetierImpl.java             # @Component("metier") avec @Autowired + @Qualifier
    │   │   └── presentation/
    │   │       ├── Presentation2.java          # Point d'entrée configuration par annotations
    │   │       ├── PresentationXML.java        # Point d'entrée configuration XML
    │   │       └── PresentationProfile.java    # Démonstration des profils dev vs prod
    │   └── resources/
    │       └── applicationContext.xml          # Déclaration et injection des beans en XML
    └── test/
        └── java/
            └── metier/
                └── MetierImplTest.java         # Tests unitaires et d'intégration (4/4 tests passants)
```

---

## ⚙️ Compilation et Tests

```bash
# Compiler le projet et exécuter les tests
mvn clean test
```

### Exécution avec annotations :
```bash
mvn exec:java -Dexec.mainClass="presentation.Presentation2"
```
**Sortie** :
```text
Version base de données (valeur = 100.0)
Résultat = 200.0
```

### Exécution avec profils (@Profile) :
```bash
mvn exec:java -Dexec.mainClass="presentation.PresentationProfile"
```
**Sortie** :
```text
=== Test avec profil 'prod' ===
Version base de données (valeur = 100.0)
Résultat [prod] = 200.0

=== Test avec profil 'dev' ===
Version capteurs (valeur = 150.0)
Résultat [dev] = 300.0
```

### Exécution avec XML :
```bash
mvn exec:java -Dexec.mainClass="presentation.PresentationXML"
```
**Sortie** :
```text
Version base de données (valeur = 100.0)
Résultat (XML avec dao) = 200.0
Version capteurs (valeur = 150.0)
Résultat (XML avec dao2) = 300.0
```

---

## 🧪 Tests Unitaires et Validation JUnit 5

1. `testCalculWithDaoImpl()` : Vérifie que le calcul double la valeur de DaoImpl (100.0 * 2 = 200.0).
2. `testCalculWithDaoImpl2()` : Vérifie que le calcul double la valeur de DaoImpl2 (150.0 * 2 = 300.0).
3. `testSpringAnnotationContext()` : Valide le chargement complet du conteneur `AnnotationConfigApplicationContext`.
4. `testSpringXmlContext()` : Valide le chargement complet du conteneur `ClassPathXmlApplicationContext`.
