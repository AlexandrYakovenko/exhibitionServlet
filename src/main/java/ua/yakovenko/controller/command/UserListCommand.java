package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserListCommand implements Command {
    private UserService userService;
    private List<User> userList;

    public UserListCommand(UserService userService) {
        this.userService = userService;
        userList = new CopyOnWriteArrayList<>();
    }

    @Override
    public String execute(HttpServletRequest request) {
        userList = userService.findAllUsers();
        request.setAttribute("userList", userList);

        return "/WEB-INF/super_admin/pages/userList.jsp";
    }
}
