package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.QuestionDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.dao.UserDAO;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;
import apps.g0rba4ev.domain.User;
import apps.g0rba4ev.util.HashGenerator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

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

        ArrayList<Survey> arr = new ArrayList<>();
        Survey survey0 = new Survey( java.sql.Date.valueOf("2020-05-29") );
        Survey survey1 = new Survey( java.sql.Date.valueOf("2020-05-30") );
        Survey survey2 = new Survey( java.sql.Date.valueOf("2020-05-31") );
        Survey survey3 = new Survey( java.sql.Date.valueOf("2020-06-01") );
        Survey survey4 = new Survey( java.sql.Date.valueOf("2020-06-02") );
        Survey survey5 = new Survey( java.sql.Date.valueOf("2020-06-03") );
        Survey survey6 = new Survey( java.sql.Date.valueOf("2020-06-04") );
        Survey survey7 = new Survey( java.sql.Date.valueOf("2020-06-05") );
        Survey survey8 = new Survey( java.sql.Date.valueOf("2020-06-06") );
        arr.add(survey0);
        arr.add(survey1);
        arr.add(survey2);
        arr.add(survey3);
        arr.add(survey4);
        arr.add(survey5);
        arr.add(survey6);
        arr.add(survey7);
        arr.add(survey8);

        for(Survey survey: arr) {
            survey.setQuestion(1, q1);
            survey.setQuestion(2, q2);
            survey.setQuestion(3, q3);
            sDAO.save(survey);
        }

        User user = new User("admin", HashGenerator.generateSHA256("asdfgh"), "admin");
        User user1 = new User("user", HashGenerator.generateSHA256("qwerty"), "user");
        UserDAO userDAO = new UserDAO();
        userDAO.save(user);
        userDAO.save(user1);

    }

}
