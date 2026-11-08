package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Implémentation concrète de l'interface IDao (version base de données).
 *
 * @author Mouad Agdouz (https://github.com/agdouz)
 */
@Component("dao")
@Profile("prod")
public class DaoImpl implements IDao {

    @Override
    public double getValue() {
        System.out.println("Version base de données (valeur = 100.0)");
        return 100.0;
    }
}
