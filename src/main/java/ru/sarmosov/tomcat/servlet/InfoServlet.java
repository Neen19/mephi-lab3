package ru.sarmosov.tomcat.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.sarmosov.model.Info;
import ru.sarmosov.service.InfoService;

import java.io.IOException;
import java.util.List;

@WebServlet("/info/*")
public class InfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        InfoService service = infoService(req.getSession(false));
        System.out.println(service.getDao());
        List<Info> infoList = service.getAll();
        req.setAttribute("list", infoList);
        req.getRequestDispatcher("view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        System.out.println(command);
        if (command != null) {
            if (command.equals("DELETE")) {
                doDelete(req, resp);
            } else doPut(req, resp);
            return;
        }
        InfoService service = infoService(req.getSession(false));
        service.save(new Info(req.getParameter("info")));
        resp.sendRedirect("/info");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InfoService service = infoService(req.getSession(false));
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        String info = req.getParameter("command");
        Info entity = new Info(id, info);
        service.update(entity);
        resp.sendRedirect("/info");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InfoService service = infoService(req.getSession(false));
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        service.delete(id);
        resp.sendRedirect("/info");
    }

    private InfoService infoService(HttpSession session) {
        return new InfoService((String) session.getAttribute("login"));
    }

}
