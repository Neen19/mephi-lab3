package ru.sarmosov.tomcat.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.startsWith("/login") || path.startsWith("/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
