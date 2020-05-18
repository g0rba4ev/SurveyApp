package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.AnswerDAO;
import apps.g0rba4ev.dao.QuestionDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.domain.Answer;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@WebServlet("/updAnswer")
public class UpdAnswerServlet extends HttpServlet {

    private QuestionDAO qDAO;
    private AnswerDAO aDAO;
    private SurveyDAO sDAO;

    @Override
    public void init() {
        qDAO = new QuestionDAO();
        aDAO = new AnswerDAO();
        sDAO = new SurveyDAO();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String userName = null;
        for(Cookie cookie: req.getCookies()) {
            if("userName".equals(cookie.getName())){
                userName = cookie.getValue();
                break;
            }
        }

        if(userName != null && !userName.equals("")) {
            String dateStr = req.getHeader("surveyDate");
            Date date = Date.valueOf(dateStr);
            Survey survey = sDAO.findByDate( Date.valueOf(dateStr) );

            for(Question question: survey.getQuestionMap().values()) {
                Answer answer = aDAO.find(userName, date, question);
                String answerStr = req.getParameter( "q_" + question.getId() );
                answer.setAnswer(answerStr);
                aDAO.update(answer);
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setHeader("Message", "Answer updated successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setHeader("Message", "Username is missing in request");
        }

    }

}
