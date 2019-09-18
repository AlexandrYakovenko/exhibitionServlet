package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Registration implements Command {
    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) return "/registration.jsp";

        User newUser = User.builder()
                .username(username)
                .password(password)
                .role(Role.USER)
                .build();
        try {
            userService.addUser(newUser);
        } catch (SQLException e) {
            request.setAttribute("error", true);
        }
        return "/registration.jsp";
    }
}

