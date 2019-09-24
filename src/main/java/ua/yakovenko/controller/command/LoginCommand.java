package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null)
            return "/login.jsp";

        /*if (nonNull(request.getSession().getAttribute("username")))
            return "/welcome.jsp";*/

        Optional<User> user = userService.findUser(username, password);

        if (!user.isPresent()) {
            request.setAttribute("error", true);
            return "/login.jsp";
        }

        if (CommandUtility.checkUserIsLogged(request, username)) {
            throw new RuntimeException("You already logged");
        }

        if (user.get().getRole().equals(Role.ADMIN)) {
            CommandUtility.setUserRole(request, Role.ADMIN, username);
            return "redirect:/exhibition/admin";
        } else if (user.get().getRole().equals(Role.SUPER_ADMIN)) {
            CommandUtility.setUserRole(request, Role.SUPER_ADMIN, username);
            return "redirect:/exhibition/superAdmin";
        } else {
            CommandUtility.setUserRole(request, Role.USER, username);
            return "redirect:/exhibition/user"; //change welcome.jsp на index.jsp
        }

    }
}
