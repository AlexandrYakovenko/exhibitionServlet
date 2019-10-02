package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

        String boughtTicketIdStr = request.getParameter("boughtTicketId");
        if (boughtTicketIdStr != null) {
            Long ticketId = Long.valueOf(boughtTicketIdStr);
            try {
                userService.buyTicket(user, ticketId);
            } catch (Exception e) {
                return "redirect:/exhibition/user/buy-ticket";
            }
        }

        List<Exhibition> exhibitions = exhibitionService.findBoughtTickets(user);

        request.setAttribute("tickets", exhibitions);

        return "/WEB-INF/user/pages/boughtTickets.jsp";
    }
}
