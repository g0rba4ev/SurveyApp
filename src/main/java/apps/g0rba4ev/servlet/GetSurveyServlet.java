package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.AnswerDAO;
import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.domain.Answer;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;
import apps.g0rba4ev.domain.User;
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
import java.util.Arrays;
import java.util.Map;


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

        User user = (User) req.getSession().getAttribute("loggedUser");

        String json = surveyToJson(survey, user, date);
        resp.setHeader("surveyDate", survey.getDate().toString());
        resp.getWriter().print(json);
    }

    /**
     * transform Survey to JSON (and if userName not null, add his answers to this JSON)
     * @param survey
     * @param user whose answers
     * @param date for finding answer (if exist)
     * @return JSON string with survey (and user's answers)
     */
    private String surveyToJson(Survey survey, User user, Date date) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode questionsArrayNode = mapper.createArrayNode();

        Map<Integer, Question> questionMap = survey.getQuestionMap();
        Integer[] keys = questionMap.keySet().toArray(new Integer[0]);
        Arrays.sort(keys);
        for(Integer i: keys) {
            ObjectNode questionNode = mapper.createObjectNode();
            Question question = questionMap.get(i);
            questionNode.put("number", i);
            questionNode.put("type", question.getType());
            questionNode.put("question", question.getQuestion());
            questionNode.put("id", question.getId());

            Answer answer = aDAO.find(user, date, question);
            if(answer != null){
                questionNode.put("answer", answer.getAnswer());

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
