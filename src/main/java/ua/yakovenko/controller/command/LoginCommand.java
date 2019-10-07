package ua.yakovenko.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;
import ua.yakovenko.controller.util.CommandUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.yakovenko.controller.util.Constants.*;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private static final String DATA_ERROR = "Invalid username or password.";

    private static final String LOGGED_ERROR = "You has already logged.";

    private static final String LOGGED_SUCCESS = " logged successfully.";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null)
            return PAGE_LOGIN;

        Optional<User> user = userService.findUser(username, password);

        if (!user.isPresent()) {
            request.setAttribute("error", DATA_ERROR);
            return PAGE_LOGIN;
        }

        if (CommandUtility.checkUserIsLogged(request, username)) {
            request.setAttribute("error", LOGGED_ERROR);
            return PAGE_LOGIN;
        }

        if (user.get().getRole().equals(Role.ADMIN)) {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + LOGGED_SUCCESS);

            return "redirect:/exhibition/" + URL_ADMIN;
        } else if (user.get().getRole().equals(Role.SUPER_ADMIN)) {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + LOGGED_SUCCESS);

            return "redirect:/exhibition/" + URL_SUPER_ADMIN;
        } else {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + LOGGED_SUCCESS);

            return "redirect:/exhibition/" + URL_USER;
        }

    }
}
