package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditProfileCommand implements Command {
    private static final String USERNAME_SAVED = "Username successfully saved";
    private static final String PASSWORD_SAVED = "Password successfully saved";
    private UserService userService;

    public EditProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        HttpSession session = request.getSession();

        User currentUser = (User) request.getSession().getAttribute("user");

        if (username != null) {
            currentUser.setUsername(username);

            userService.update(currentUser);

            CommandUtility.setUser(request, currentUser);
            request.setAttribute("message", USERNAME_SAVED);
        }

        if (password != null && passwordConfirm != null) {
            if (password.equals(passwordConfirm) ) {
                currentUser.setPassword(password);

                userService.update(currentUser);

                CommandUtility.setUser(request, currentUser);
                request.setAttribute("message", PASSWORD_SAVED);
            } else {
                request.setAttribute("error", "Invalid confirm password.");
            }
        }

        request.setAttribute("oldUsername",  session.getAttribute("username"));

        return "/WEB-INF/user/pages/editProfile.jsp";
    }
}
