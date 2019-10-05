package ua.yakovenko.controller.command;

import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegistrationCommand implements Command {

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null)
            return "/registration.jsp";

        try {
            userService.addUser(username, password);
        } catch (SQLException e) {
            request.setAttribute("error", true);
        }

        return "redirect:/exhibition/login";
    }
}

