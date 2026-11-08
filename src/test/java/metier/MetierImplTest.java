package metier;

import dao.DaoImpl;
import dao.DaoImpl2;
import dao.IDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import presentation.Presentation2;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires et d'intégration validant le conteneur Spring IoC et l'injection de dépendances.
 *
 * @author Mouad Agdouz (https://github.com/agdouz)
 */
public class MetierImplTest {

    @Test
    public void testCalculWithDaoImpl() {
        IDao dao = new DaoImpl();
        MetierImpl metier = new MetierImpl(dao);

        double res = metier.calcul();
        assertEquals(200.0, res, 0.001, "Calcul avec DaoImpl doit valoir 100 * 2 = 200");
    }

    @Test
    public void testCalculWithDaoImpl2() {
        IDao dao2 = new DaoImpl2();
        MetierImpl metier = new MetierImpl(dao2);

        double res = metier.calcul();
        assertEquals(300.0, res, 0.001, "Calcul avec DaoImpl2 doit valoir 150 * 2 = 300");
    }

    @Test
    public void testSpringAnnotationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(Presentation2.class);
        context.refresh();

        IMetier metier = context.getBean(IMetier.class);
        assertNotNull(metier, "Le bean métier doit être injecté et non null");
        assertEquals(200.0, metier.calcul(), 0.001);

        context.close();
    }

    @Test
    public void testSpringXmlContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        IMetier metier = (IMetier) context.getBean("metier");
        assertNotNull(metier);
        assertEquals(200.0, metier.calcul(), 0.001);

        IMetier metierDev = (IMetier) context.getBean("metierDev");
        assertNotNull(metierDev);
        assertEquals(300.0, metierDev.calcul(), 0.001);

        context.close();
    }
}
