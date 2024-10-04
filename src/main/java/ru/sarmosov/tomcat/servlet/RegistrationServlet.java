package ru.sarmosov.tomcat.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.sarmosov.service.RegistrationService;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private RegistrationService registrationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        registrationService = (RegistrationService) getServletContext().getAttribute("registrationService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/registration.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        registrationService.register(login, password);
        session.setAttribute("isAuth", true);
        session.setAttribute("login", login);
        resp.sendRedirect("/info");
    }

}
