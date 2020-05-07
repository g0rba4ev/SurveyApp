package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.AnswerDAO;
import apps.g0rba4ev.dao.QuestionDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.dao.UserDAO;
import apps.g0rba4ev.domain.Answer;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;
import apps.g0rba4ev.domain.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/postAnswer")
public class PostAnswerServlet extends HttpServlet {

    private UserDAO uDAO;
    private QuestionDAO qDAO;
    private AnswerDAO aDAO;
    private SurveyDAO sDAO;

    @Override
    public void init() {
        uDAO = new UserDAO();
        qDAO = new QuestionDAO();
        aDAO = new AnswerDAO();
        sDAO = new SurveyDAO();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        //for test
        req.getSession().setAttribute("user_id", 1);
        //end for test

        Integer userId = (Integer) req.getSession().getAttribute("user_id");

        User user = uDAO.findById(userId);
        if(user != null) {
            String dateStr = req.getHeader("surveyDate");
            Date date = Date.valueOf(dateStr);
            Survey survey = sDAO.findByDate( Date.valueOf(dateStr) );

            for(Question question: survey.getQuestionsSet()) {
                String answerStr = req.getParameter( "q_" + question.getId() );
                Answer answer = new Answer(question, user, answerStr, date);
                aDAO.save(answer);
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setHeader("Message", "Answer saved successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setHeader("Message", "User not found");
        }

    }

}
