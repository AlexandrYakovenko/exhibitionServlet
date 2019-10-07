package ua.yakovenko.controller.command;

import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.yakovenko.controller.util.Constants.*;

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
            return PAGE_REGISTRATION;

        userService.addUser(username, password);

        return "redirect:/exhibition/" + URL_LOGIN;
    }
}

