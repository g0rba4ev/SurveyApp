package apps.g0rba4ev.dao;

import apps.g0rba4ev.domain.Survey;
import apps.g0rba4ev.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;

public class SurveyDAO {

    public Survey findByDate(Date surveyDate) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Survey.class, surveyDate);
    }

    public void save(Survey survey) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(survey);
        tx1.commit();
        session.close();
    }

    public void update(Survey survey) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(survey);
        tx1.commit();
        session.close();
    }

}
