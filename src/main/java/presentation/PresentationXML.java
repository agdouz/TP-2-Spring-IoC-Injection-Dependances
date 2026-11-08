package presentation;

import metier.IMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Point d'entrée de l'application basé sur la configuration XML classique.
 * Initialise le conteneur Spring avec ClassPathXmlApplicationContext.
 *
 * @author Mouad Agdouz (https://github.com/agdouz)
 */
public class PresentationXML {

    public static void main(String[] args) {
        // Chargement du contexte Spring à partir du fichier XML
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Récupération du bean métier injecté avec dao (base de données)
        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println("Résultat (XML avec dao) = " + metier.calcul());

        // Récupération du bean métier injecté avec dao2 (capteurs)
        IMetier metierDev = (IMetier) context.getBean("metierDev");
        System.out.println("Résultat (XML avec dao2) = " + metierDev.calcul());

        context.close();
    }
}
