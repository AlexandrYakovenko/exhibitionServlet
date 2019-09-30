package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserEditCommand implements Command {
    private UserService userService;

    public UserEditCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long editId = Long.valueOf(request.getParameter("userId"));
        User editUser = userService.findById(editId);
        request.setAttribute("editUser", editUser);

        String newUsername = request.getParameter("newUsername");
        String newRole = request.getParameter("newRole");

        try {
            if (newUsername != null && newRole != null) {
                editUser.setUsername(newUsername);
                editUser.setRole(Role.valueOf(newRole));
                userService.update(editUser);
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
        return "/WEB-INF/super_admin/pages/userEdit.jsp";
    }
}
