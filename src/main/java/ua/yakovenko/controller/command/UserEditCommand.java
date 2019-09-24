package ua.yakovenko.controller.command;

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
        Long editId = (Long) request.getSession().getAttribute("editId");
        User editUser = userService.findById(editId).get();

        request.setAttribute("editUsername", editUser.getUsername());
        request.setAttribute("editRole", editUser.getRole());

        return "/WEB-INF/super_admin/parts/userEdit.jsp";
    }
}
