package presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Point d'entrée de l'application basé sur la configuration par annotations.
 * Initialise le conteneur Spring IoC avec AnnotationConfigApplicationContext.
 *
 * @author Mouad Agdouz (https://github.com/agdouz)
 */
@Configuration
@ComponentScan(basePackages = {"dao", "metier"})
public class Presentation2 {

    public static void main(String[] args) {
        // Initialisation du conteneur Spring basé sur les annotations
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // Activer le profil par défaut si besoin
        context.getEnvironment().setActiveProfiles("prod");
        context.register(Presentation2.class);
        context.refresh();

        // Récupération du bean métier
        IMetier metier = context.getBean(IMetier.class);

        // Exécution et affichage du résultat
        double resultat = metier.calcul();
        System.out.println("Résultat = " + resultat);

        context.close();
    }
}
