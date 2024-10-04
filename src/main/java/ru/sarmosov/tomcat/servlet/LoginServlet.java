package ru.sarmosov.tomcat.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sarmosov.service.AuthService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = (AuthService) getServletContext().getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (authService.auth(login, password)) {
            if (req.getSession(false) == null) {
                req.getSession().setAttribute("login", login);
            }
            resp.sendRedirect("/info");
        } else {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/HTML");
            resp.getWriter().write("Неверный логин пароль");
        }
    }

}
