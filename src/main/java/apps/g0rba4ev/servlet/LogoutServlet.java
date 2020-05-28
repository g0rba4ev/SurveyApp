package apps.g0rba4ev.servlet;

import apps.g0rba4ev.dao.UserAuthDAO;
import apps.g0rba4ev.domain.UserAuthToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("loggedUser");
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            String selector = "";

            for (Cookie aCookie : cookies) {
                if (aCookie.getName().equals("selector")) {
                    selector = aCookie.getValue();
                }
            }

            if (!selector.isEmpty()) {
                // delete token from database
                UserAuthDAO authDAO = new UserAuthDAO();
                UserAuthToken token = authDAO.findBySelector(selector);

                if (token != null) {
                    authDAO.delete(token);

                    Cookie cookieSelector = new Cookie("selector", "");
                    Cookie cookieValidator = new Cookie("validator", "");
                    cookieSelector.setMaxAge(0);
                    cookieValidator.setMaxAge(0);
                    resp.addCookie(cookieSelector);
                    resp.addCookie(cookieValidator);
                }
            }
        }

        resp.sendRedirect(req.getContextPath());
    }
}
