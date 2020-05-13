package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.AnswerDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet("/getSurvey")
public class GetSurveyServlet extends HttpServlet {
    private SurveyDAO sDAO;
    private AnswerDAO aDAO;

    @Override
    public void init() {
        sDAO = new SurveyDAO();
        aDAO = new AnswerDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // get current date
        Date date = new Date( new java.util.Date().getTime() );

        Survey survey = sDAO.findByDate(date);
        if(survey == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setHeader("Message", "Survey not found");
            return;
        }

        String userName = null;
        for(Cookie cookie: req.getCookies()) {
            if("userName".equals(cookie.getName())){
                userName = cookie.getValue();
                break;
            }
        }

        String json = surveyToJson(survey, userName, date);
        resp.setHeader("surveyDate", survey.getDate().toString());
        resp.setHeader("userName", userName);
        resp.getWriter().print(json);
    }

    /**
     * transform Survey to JSON (and if userName not null, add his answers to this JSON)
     * @param survey
     * @param userName whose answers
     * @param date
     * @return JSON string with survey (and user's answers)
     */
    private String surveyToJson(Survey survey, String userName, Date date) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode questionsArrayNode = mapper.createArrayNode();

        for(Question question: survey.getQuestionsSet()) {
            ObjectNode questionNode = mapper.createObjectNode();
            questionNode.put("type", question.getType());
            questionNode.put("question", question.getQuestion());
            questionNode.put("id", question.getId());
            if(userName != null) {
                String answer = aDAO.find(userName, date, question).getAnswer();
                questionNode.put("answer", answer);
            }
            questionsArrayNode.add(questionNode);
        }

        String json;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(questionsArrayNode);
        } catch (IOException ioe) {
            // TODO handle this exception
            json = "";
        }
        return json;
    }

}
