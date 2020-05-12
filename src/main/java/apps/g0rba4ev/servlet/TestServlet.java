package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.AnswerDAO;
import apps.g0rba4ev.dao.QuestionDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.dao.UserDAO;
import apps.g0rba4ev.domain.Answer;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;
import apps.g0rba4ev.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        UserDAO uDAO = new UserDAO();
        QuestionDAO qDAO = new QuestionDAO();
        AnswerDAO aDAO = new AnswerDAO();
        SurveyDAO sDAO = new SurveyDAO();

        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setRole("admin");
        uDAO.save(user);

        Question q1 = new Question("Question 1 ?", "RADIO");
        Question q2 = new Question("Question 2 ?", "RADIO");
        Question q3 = new Question("Question 3 ?", "TEXT");
        Question q4 = new Question("Question 4 ?", "RADIO");
        qDAO.save(q1);
        qDAO.save(q2);
        qDAO.save(q3);
        qDAO.save(q4);

        Survey survey1 = new Survey( java.sql.Date.valueOf("2020-05-12") );
        survey1.addQuestion(q1);
        survey1.addQuestion(q2);
        survey1.addQuestion(q3);
        Survey survey2 = new Survey( java.sql.Date.valueOf("2018-12-11") );
        survey2.addQuestion(q2);
        survey2.addQuestion(q3);
        sDAO.save(survey2);
        sDAO.save(survey1);
    }

}
