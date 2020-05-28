package apps.g0rba4ev.dao;

import apps.g0rba4ev.domain.UserAuthToken;
import apps.g0rba4ev.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserAuthDAO {

    /**
     * find UserAuthToken by field "selector"
     * @param selector
     * @return UserAuthToken (or null if token not found)
     */
    public UserAuthToken findBySelector(String selector) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM UserAuthToken as token where token.selector=:selector");
            query.setParameter("selector", selector);
            List listAnswers = query.list();
            if(!listAnswers.isEmpty()){
                return (UserAuthToken) query.list().get(0);
            } else {
                return null;
            }
        }
    }

    public void update(UserAuthToken token) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(token);
        tx1.commit();
        session.close();
    }

    public void save(UserAuthToken token) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(token);
        tx1.commit();
        session.close();
    }

    public void delete(UserAuthToken token) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(token);
        tx1.commit();
        session.close();
    }

}
