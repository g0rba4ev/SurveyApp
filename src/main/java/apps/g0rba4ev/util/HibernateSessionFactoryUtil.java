package apps.g0rba4ev.util;

import apps.g0rba4ev.domain.Answer;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Question.class);
                configuration.addAnnotatedClass(Answer.class);
                configuration.addAnnotatedClass(Survey.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
        }
        return sessionFactory;
    }
}
