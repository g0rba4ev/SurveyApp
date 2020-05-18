package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.QuestionDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        QuestionDAO qDAO = new QuestionDAO();
        SurveyDAO sDAO = new SurveyDAO();

        Question q1 = new Question("Rate this survey ?", "RADIO");
        Question q2 = new Question("Question 2 ?", "RADIO");
        Question q3 = new Question("Enter some text ?", "TEXT");
        Question q4 = new Question("Question 4 ?", "RADIO");
        qDAO.save(q1);
        qDAO.save(q2);
        qDAO.save(q3);
        qDAO.save(q4);

        Survey survey1 = new Survey( java.sql.Date.valueOf("2020-05-18") );
        Survey survey2 = new Survey( java.sql.Date.valueOf("2020-05-19") );
        Survey survey3 = new Survey( java.sql.Date.valueOf("2020-05-20") );
        Survey survey4 = new Survey( java.sql.Date.valueOf("2020-05-21") );

        survey1.setQuestion(1, q1);
        survey1.setQuestion(2, q3);
        survey2.setQuestion(1, q2);
        survey2.setQuestion(2, q3);
        survey3.setQuestion(1, q3);
        survey3.setQuestion(2, q4);
        survey4.setQuestion(1, q4);
        survey4.setQuestion(2, q1);

        sDAO.save(survey1);
        sDAO.save(survey2);
        sDAO.save(survey3);
        sDAO.save(survey4);

    }

}
