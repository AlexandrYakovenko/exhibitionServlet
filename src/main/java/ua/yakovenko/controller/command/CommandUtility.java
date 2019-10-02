package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

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

    static void showPagination(HttpServletRequest request,
                               ExhibitionService eService
    ) {
        int page = 1;
        int recordsPerPage = 5;
        int numberOfRecords = eService.countOfRecords();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Exhibition> exhibitions =
                eService.findDiapason((page - 1) * recordsPerPage,
                        recordsPerPage);

        request.setAttribute("exhibitionList", exhibitions);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
    }
}
