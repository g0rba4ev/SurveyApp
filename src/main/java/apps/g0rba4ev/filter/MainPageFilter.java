package apps.g0rba4ev.filter;

import apps.g0rba4ev.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MainPageFilter")
public class MainPageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;
        User user = (User) req.getSession().getAttribute("loggedUser");

        if(user.getRole().equals("admin")) {
            req.getRequestDispatcher("/WEB-INF/views/admin_page.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/user_page.jsp").forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
