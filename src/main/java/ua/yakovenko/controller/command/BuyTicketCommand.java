package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BuyTicketCommand implements Command {
    private ExhibitionService exhibitionService;
    private UserService userService;

    public BuyTicketCommand(ExhibitionService exhibitionService, UserService userService) {
        this.exhibitionService = exhibitionService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String ticketIdString = request.getParameter("ticketId");

        if (ticketIdString != null) {
            Long ticket = Long.valueOf(ticketIdString);
            request.getSession().setAttribute("ticketId", ticket);
        }

        Long ticketId = (Long) request.getSession().getAttribute("ticketId");
        Exhibition exhibition = exhibitionService.findById(ticketId);

        Long userId = (Long) request.getSession().getAttribute("userId");
        User currentUser = userService.findById(userId);

        request.setAttribute("currentUser", currentUser);
        request.setAttribute("exhibition", exhibition);
        
        return "/WEB-INF/user/pages/buyTicket.jsp";
    }
}
