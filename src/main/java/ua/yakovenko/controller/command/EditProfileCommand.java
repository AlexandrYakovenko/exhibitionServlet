package ua.yakovenko.controller.command;

import ua.yakovenko.controller.util.CommandUtility;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.yakovenko.controller.util.Constants.*;

public class EditProfileCommand implements Command {

    private static final String USERNAME_SAVED = "Username successfully saved";

    private static final String PASSWORD_SAVED = "Password successfully saved";

    private static final String CONFIRM_ERROR = "Invalid confirm password.";

    private UserService userService;

    public EditProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        User currentUser = userService.findById(userId);

        String username = request.getParameter("username");
        if (username != null) {
            currentUser.setUsername(username);

            userService.update(currentUser);

            CommandUtility.setUser(request, currentUser);
            request.setAttribute("message", USERNAME_SAVED);
        }

        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        if (password != null && passwordConfirm != null) {
            if (password.equals(passwordConfirm) ) {
                currentUser.setPassword(password);

                userService.update(currentUser);

                CommandUtility.setUser(request, currentUser);
                request.setAttribute("message", PASSWORD_SAVED);
            } else {
                request.setAttribute("error", CONFIRM_ERROR);
            }
        }

        return PAGE_EDIT_PROFILE;
    }
}
