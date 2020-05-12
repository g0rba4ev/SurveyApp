package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.SurveyDAO;
import apps.g0rba4ev.domain.Question;
import apps.g0rba4ev.domain.Survey;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet("/getSurvey")
public class GetSurveyServlet extends HttpServlet {
    private SurveyDAO sDAO;

    @Override
    public void init() {
        sDAO = new SurveyDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String dateStr = req.getParameter("surveyDate");
        Date date;
        if (dateStr != null) {
            try {
                date = Date.valueOf(dateStr);
            } catch (IllegalArgumentException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setHeader("Message", "Incorrect param: surveyDate");
                return;
            }
        } else {
            // get current date
            date = new Date( new java.util.Date().getTime() );
        }

        Survey survey = sDAO.findByDate(date);
        if(survey == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setHeader("Message", "Survey not found");
            return;
        }

        String json = surveyToJson(survey);
        resp.setHeader("surveyDate", survey.getDate().toString());
        resp.getWriter().print(json);
    }

    private String surveyToJson(Survey survey) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode questionsArrayNode = mapper.createArrayNode();

        for(Question question: survey.getQuestionsSet()) {
            ObjectNode questionNode = mapper.createObjectNode();
            questionNode.put("type", question.getType());
            questionNode.put("question", question.getQuestion());
            questionNode.put("id", question.getId());
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
