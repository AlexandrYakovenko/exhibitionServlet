package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.yakovenko.controller.util.Constants.*;

public class UserEditCommand implements Command {

    private UserService userService;

    public UserEditCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Long userId = Long.valueOf(request.getParameter(USER_ID));
            User user = userService.findById(userId);
            request.setAttribute("editUser", user);

            String newUsername = request.getParameter("newUsername");
            String newRole = request.getParameter("newRole");
            if (newUsername != null && newRole != null) {
                user.setUsername(newUsername);
                user.setRole(Role.valueOf(newRole));
                userService.update(user);
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
        return PAGE_USER_EDIT;
    }
}
