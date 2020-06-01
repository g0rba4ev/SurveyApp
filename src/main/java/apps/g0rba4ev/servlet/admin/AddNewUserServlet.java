package apps.g0rba4ev.servlet.admin;

import apps.g0rba4ev.dao.UserDAO;
import apps.g0rba4ev.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/addNewUser")
public class AddNewUserServlet extends HttpServlet {

    private UserDAO uDAO;

    @Override
    public void init() {
        uDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if ( login != null && password != null && role != null &&
                !login.isEmpty() && !password.isEmpty() && !role.isEmpty() ) {
            if (uDAO.findByLogin(login) == null) {
                User user = new User(login, password, role);
                uDAO.save(user);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setHeader("Message", "User added successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setHeader("Message", "User with this login already exist");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setHeader("Message", "One of fields either login or password is empty");
        }
    }

}
