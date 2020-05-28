package apps.g0rba4ev.dao;

import apps.g0rba4ev.domain.User;
import apps.g0rba4ev.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    /**
     * find User by login
     * @return User (or null if User not found)
     */
    public User findByLogin(String login) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User as user where user.login=:login");
            query.setParameter("login", login);
            List listAnswers = query.list();
            if(listAnswers.isEmpty()){
                return null;
            } else {
                return (User) query.list().get(0);
            }
        }
    }

}
