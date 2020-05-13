package apps.g0rba4ev.dao;

import apps.g0rba4ev.domain.Answer;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class AnswerDAO {

    public Answer findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Answer.class, id);
    }

    public void save(Answer answer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(answer);
        tx1.commit();
        session.close();
    }

    public void update(Answer answer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(answer);
        tx1.commit();
        session.close();
    }

    /**
     * find Answer by userName, Date and Question
     * @return Answer
     */
    public Answer find(String userName, Date date, Question question) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Answer as answer where " +
                    "answer.question=:question and answer.userName=:userName and answer.date=:date");
            query.setParameter("question", question);
            query.setParameter("userName", userName);
            query.setParameter("date", date);
            List listAnswers = query.list();
            if(listAnswers.isEmpty()){
                return null;
            } else {
                return (Answer) query.list().get(0);
            }

        }
    }


}
