package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.AnswerDAO;
import apps.g0rba4ev.dao.QuestionDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.domain.Answer;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Map;

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

        User user = (User) req.getSession().getAttribute("loggedUser");
        String dateStr = req.getHeader("surveyDate");
        Date date = Date.valueOf(dateStr);
        Map<Integer, Question> questionMap = sDAO.findByDate(date).getQuestionMap();
        //check - is answer of this user already accepted today
        if(!questionMap.isEmpty()) {
            Question anyQuestion = questionMap.values().iterator().next(); // get any question for check
            if( aDAO.find(user, date, anyQuestion) != null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setHeader("Message", "Error: today you already sent the answers, you can only update it");
                return;
            }
        }

        for(Question question: questionMap.values()) {
            String answerStr = req.getParameter( "q_" + question.getId() );
            Answer answer = new Answer(question, user, answerStr, date);
            aDAO.save(answer);
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setHeader("Message", "Answer saved successfully");

    }

}
