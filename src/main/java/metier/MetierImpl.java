package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Implémentation concrète de l'interface IMetier.
 * Dépend de l'interface IDao (inversion de contrôle et couplage faible).
 *
 * @author Mouad Agdouz (https://github.com/agdouz)
 */
@Component("metier")
public class MetierImpl implements IMetier {

    @Autowired
    @Qualifier("dao")
    private IDao dao;

    public MetierImpl() {
    }

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    public IDao getDao() {
        return dao;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("Le DAO n'a pas été injecté.");
        }
        return dao.getValue() * 2;
    }
}
