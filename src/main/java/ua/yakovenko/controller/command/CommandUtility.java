package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {
    static void setUser(HttpServletRequest request,
                            User user
    ) {
        HttpSession session = request.getSession();

        session.setAttribute("user", user);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userId", user.getId());
        session.setAttribute("role",user.getRole());
        session.setAttribute("access", user.getRole().name());
    }

    static boolean checkUserIsLogged(HttpServletRequest request,
                                     String username
    ) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");

        if (loggedUsers == null) { return false; }

        if (loggedUsers.stream().anyMatch(username::equalsIgnoreCase)) { return true; }

        loggedUsers.add(username);

        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        return false;
    }
}
