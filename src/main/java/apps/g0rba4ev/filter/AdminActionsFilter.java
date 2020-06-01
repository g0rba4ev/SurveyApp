package apps.g0rba4ev.filter;

import apps.g0rba4ev.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminActionsFilter")
public class AdminActionsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;
        User user = (User) req.getSession().getAttribute("loggedUser");
        if(user.getRole().equals("admin")) {
            chain.doFilter(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setHeader("Message", "Access denied");
        }
    }

}
