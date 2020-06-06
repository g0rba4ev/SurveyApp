package apps.g0rba4ev.servlet.admin;

import apps.g0rba4ev.dao.UserDAO;
import apps.g0rba4ev.domain.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/getUsers")
public class GetUsersServlet extends HttpServlet {

    private UserDAO uDAO;

    @Override
    public void init() {
        uDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode usersNode = mapper.createArrayNode();

        for(User user : uDAO.getAll()) {
            ObjectNode userNode = mapper.createObjectNode();
            userNode.put("login", user.getLogin());
            userNode.put("role", user.getRole());
            usersNode.add(userNode);
        }

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usersNode);
        resp.getWriter().print(json);
    }

}
