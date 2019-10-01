package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BoughtTicketsCommand implements Command {
    private ExhibitionService exhibitionService;
    private UserService userService;
    public BoughtTicketsCommand(ExhibitionService exhibitionService, UserService userService) {
        this.exhibitionService = exhibitionService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String ticketIdStr = request.getParameter("ticketId");

        if (ticketIdStr != null) {
            Long ticketId = Long.valueOf(ticketIdStr);
            try {
                userService.buyTicket(user, ticketId);
            } catch (Exception e) {
                return "redirect:/exhibition/buy-ticket";
            }
        }

        return "/WEB-INF/user/pages/boughtTickets.jsp";
    }
}
