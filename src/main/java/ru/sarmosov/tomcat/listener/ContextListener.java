package ru.sarmosov.tomcat.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.sarmosov.service.AuthService;
import ru.sarmosov.service.RegistrationService;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("authService", new AuthService());
        servletContext.setAttribute("registrationService", new RegistrationService());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
