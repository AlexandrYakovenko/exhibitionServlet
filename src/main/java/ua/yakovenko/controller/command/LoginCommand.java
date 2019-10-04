package ua.yakovenko.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String DATA_ERROR = "Invalid username or password.";
    private static final String LOGGED_ERROR = "You has already logged.";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null)
            return "/login.jsp";

        Optional<User> user = userService.findUser(username, password);

        if (!user.isPresent()) {
            request.setAttribute("error", DATA_ERROR);
            return "/login.jsp";
        }

        if (CommandUtility.checkUserIsLogged(request, username)) {
            request.setAttribute("error", LOGGED_ERROR);
            return "/login.jsp";
        }

        if (user.get().getRole().equals(Role.ADMIN)) {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + " logged successfully.");

            return "redirect:/exhibition/admin";
        } else if (user.get().getRole().equals(Role.SUPER_ADMIN)) {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + " logged successfully.");

            return "redirect:/exhibition/super_admin";
        } else {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + " logged successfully.");

            return "redirect:/exhibition/user";
        }

    }
}
