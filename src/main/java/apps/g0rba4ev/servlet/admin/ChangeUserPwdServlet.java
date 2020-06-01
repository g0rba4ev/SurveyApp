package apps.g0rba4ev.servlet.admin;

import apps.g0rba4ev.dao.UserDAO;
import apps.g0rba4ev.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/changeUserPwd")
public class ChangeUserPwdServlet extends HttpServlet {

    private UserDAO uDAO;

    @Override
    public void init() {
        uDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if ( login != null && password != null && !login.isEmpty() && !password.isEmpty() ) {
            User user = uDAO.findByLogin(login);
            if (user != null) {
                user.setPassword(password);
                uDAO.update(user);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setHeader("Message", "Password changed successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setHeader("Message", "User with this login does not exist");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setHeader("Message", "One of fields either login or password is empty");
        }
    }
}
