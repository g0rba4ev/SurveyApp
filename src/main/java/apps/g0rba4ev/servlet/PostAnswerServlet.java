package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.AnswerDAO;
import apps.g0rba4ev.dao.QuestionDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.domain.Answer;
import apps.g0rba4ev.domain.Question;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@WebServlet("/postAnswer")
public class PostAnswerServlet extends HttpServlet {

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

        String userName = req.getHeader("userName");
        if(userName != null && !userName.equals("")) {
            String dateStr = req.getHeader("surveyDate");
            Date date = Date.valueOf(dateStr);
            Set<Question> questionsSet = sDAO.findByDate( date ).getQuestionsSet();
            //check is answer with this userName already accepted today
            if(!questionsSet.isEmpty()) {
                Answer answer = aDAO.find(userName, date, questionsSet.iterator().next());
                if(answer != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.setHeader("Message", "Sorry, today this name is already taken");
                    return;
                }
            }

            for(Question question: questionsSet) {
                String answerStr = req.getParameter( "q_" + question.getId() );
                Answer answer = new Answer(question, userName, answerStr, date);
                aDAO.save(answer);
            }
            // cookie that delete yourself after midnight
            Cookie cookie = new Cookie("userName", userName);
            LocalTime current = LocalTime.now();
            int seconds = (int) ChronoUnit.SECONDS.between(current, LocalTime.MAX);
            cookie.setMaxAge(seconds);

            resp.addCookie(cookie);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setHeader("Message", "Answer saved successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setHeader("Message", "User name field is empty");
        }

    }

}
