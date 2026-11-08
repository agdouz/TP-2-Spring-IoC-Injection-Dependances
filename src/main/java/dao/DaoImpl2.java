package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Deuxième implémentation concrète de l'interface IDao (version capteurs).
 *
 * @author Mouad Agdouz (https://github.com/agdouz)
 */
@Component("dao2")
@Profile("dev")
public class DaoImpl2 implements IDao {

    @Override
    public double getValue() {
        System.out.println("Version capteurs (valeur = 150.0)");
        return 150.0;
    }
}
