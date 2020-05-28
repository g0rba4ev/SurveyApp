package apps.g0rba4ev.filter;


import apps.g0rba4ev.dao.UserAuthDAO;
import apps.g0rba4ev.dao.UserDAO;
import apps.g0rba4ev.domain.User;
import apps.g0rba4ev.domain.UserAuthToken;
import apps.g0rba4ev.util.HashGenerator;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;
        final HttpSession session = req.getSession();

        if (req.getRequestURI().matches(".*(css|jpg|png|gif|js)")) {
            chain.doFilter(req, resp);
            return;
        }

        boolean loggedIn = session.getAttribute("loggedUser") != null;
        Cookie[] cookies = req.getCookies();

        if (!loggedIn) {
            // Redirect to the base page if we are not on the base page right now
            if(!"/".equals( req.getRequestURI().substring(req.getContextPath().length()) )){
                resp.sendRedirect(req.getContextPath());
                return;
            }
            // attempt to login by cookies
            UserAuthToken token = tryLoginByCookies(cookies);
            if(token != null) { // means successful login by cookies
                updateToken(token, resp);
                session.setAttribute("loggedUser", token.getUser());
                chain.doFilter(req, resp);
                return;
            }
            // attempt to login by request parameters
            User user = tryLoginByRequestParams(req);
            if (user != null) { // means successful first login
                createToken(user, resp);
                session.setAttribute("loggedUser", user);
                chain.doFilter(req, resp);
                return;
            }
            // user not login neither by cookies or headers
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    /**
     * try login by request parameters "login" and "password"
     * and return User if login successful, otherwise return null
     * @param req to access to the parameters login and password
     * @return User if login successful, otherwise null
     */
    private User tryLoginByRequestParams(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if( login != null && password != null && !login.isEmpty() && !password.isEmpty() ) {
            UserDAO uDAO = new UserDAO();
            User user = uDAO.findByLogin(login);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * try login by cookies: if cookies contains valid "selector" and "validator"
     * than return UserAuthToken, otherwise return null
     * @param cookies where keeps selector and validator
     * @return UserAuthToken if login successful, otherwise null
     */
    private UserAuthToken tryLoginByCookies(Cookie[] cookies) {
        if (cookies != null) {
            String selector = "";
            String rawValidator = "";

            for (Cookie aCookie : cookies) {
                if (aCookie.getName().equals("selector")) {
                    selector = aCookie.getValue();
                } else if (aCookie.getName().equals("validator")) {
                    rawValidator = aCookie.getValue();
                }
            }

            if (!"".equals(selector) && !"".equals(rawValidator)) {
                UserAuthDAO authDAO = new UserAuthDAO();
                UserAuthToken token = authDAO.findBySelector(selector);

                if (token != null) {
                    String hashedValidatorDatabase = token.getValidator();
                    String hashedValidatorCookie = HashGenerator.generateSHA256(rawValidator);
                    if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
                        return token;
                    }
                }
            }
        }
        return null;
    }

    /**
     * update token and corresponding cookies
     * @param token for update
     * @param resp for setting cookies
     */
    private void updateToken(UserAuthToken token, HttpServletResponse resp){
        // update new token in database
        String newSelector = RandomStringUtils.randomAlphanumeric(12);
        String newRawValidator = RandomStringUtils.randomAlphanumeric(64);

        String newHashedValidator = HashGenerator.generateSHA256(newRawValidator);

        token.setSelector(newSelector); // TODO !!! except case with double validator
        token.setValidator(newHashedValidator);
        UserAuthDAO authDAO = new UserAuthDAO();
        authDAO.update(token);

        // update cookie
        Cookie cookieSelector = new Cookie("selector", newSelector);
        cookieSelector.setMaxAge(604800); // 7 days

        Cookie cookieValidator = new Cookie("validator", newRawValidator);
        cookieValidator.setMaxAge(604800); // 7 days

        resp.addCookie(cookieSelector);
        resp.addCookie(cookieValidator);
    }

    private void createToken(User user, HttpServletResponse resp) {
        UserAuthToken token = new UserAuthToken(user);

        // update new token in database
        String newSelector = RandomStringUtils.randomAlphanumeric(12);
        String newRawValidator = RandomStringUtils.randomAlphanumeric(64);

        String newHashedValidator = HashGenerator.generateSHA256(newRawValidator);

        token.setSelector(newSelector); // TODO !!! except case with double validator
        token.setValidator(newHashedValidator);
        UserAuthDAO authDAO = new UserAuthDAO();
        authDAO.save(token);

        // update cookie
        Cookie cookieSelector = new Cookie("selector", newSelector);
        cookieSelector.setMaxAge(604800); // 7 days

        Cookie cookieValidator = new Cookie("validator", newRawValidator);
        cookieValidator.setMaxAge(604800); // 7 days

        resp.addCookie(cookieSelector);
        resp.addCookie(cookieValidator);
    }

}