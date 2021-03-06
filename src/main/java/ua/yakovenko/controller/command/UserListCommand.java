package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.yakovenko.controller.util.Constants.*;

public class UserListCommand implements Command {

    private UserService userService;

    public UserListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> userList = userService.findAllUsers();
        request.setAttribute("userList", userList);

        return PAGE_USER_LIST;
    }
}
