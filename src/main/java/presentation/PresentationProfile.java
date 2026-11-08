package presentation;

import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Démonstration de l'activation des profils Spring (@Profile) :
 * - Profil 'prod' active DaoImpl (valeur 100.0) -> résultat 200.0
 * - Profil 'dev' active DaoImpl2 (valeur 150.0) -> résultat 300.0
 *
 * @author Mouad Agdouz (https://github.com/agdouz)
 */
public class PresentationProfile {

    public static void main(String[] args) {
        System.out.println("=== Test avec profil 'prod' ===");
        runWithProfile("prod");

        System.out.println("\n=== Test avec profil 'dev' ===");
        runWithProfile("dev");
    }

    private static void runWithProfile(String profile) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles(profile);
        context.register(Presentation2.class);
        context.refresh();

        IMetier metier = context.getBean(IMetier.class);
        System.out.println("Résultat [" + profile + "] = " + metier.calcul());

        context.close();
    }
}
