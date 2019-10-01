package ua.yakovenko.controller;

import ua.yakovenko.controller.command.*;
import ua.yakovenko.model.service.ExhibitionService;
import ua.yakovenko.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        UserService userService = new UserService();
        ExhibitionService exhibitionService = new ExhibitionService();

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login", new LoginCommand(userService));
        commands.put("registration", new RegistrationCommand(userService));
        commands.put("logout", new LogOutCommand());
        commands.put("exception", new ExceptionCommand());
        commands.put("admin", new AdminCommand());
        commands.put("user", new UserCommand());
        commands.put("superAdmin", new SuperAdminCommand());
        commands.put("super_admin/userList", new UserListCommand(userService));
        commands.put("super_admin/edit", new UserEditCommand(userService));
        commands.put("exhibitions", new ExhibitionPageCommand(exhibitionService));
        commands.put("admin/exhibitions/add", new ExhibitionAddCommand(exhibitionService));
        commands.put("admin/exhibitions/edit", new ExhibitionEditCommand(exhibitionService));
        commands.put("admin/myExhibitions", new MyExhibitionsCommand(exhibitionService));
        commands.put("user/editProfile", new EditProfileCommand(userService));
        commands.put("buy-ticket", new BuyTicketCommand(exhibitionService));
        commands.put("add-money", new AddMoneyCommand(userService));
        commands.put("bought-tickets", new BoughtTicketsCommand(exhibitionService, userService));
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp
    ) throws ServletException, IOException {

        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp
    ) throws ServletException, IOException {

        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response
    ) throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/exhibition/", "");

        Command command = commands.getOrDefault(path, (r) -> "/WEB-INF/error404.jsp");

        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        }  else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
