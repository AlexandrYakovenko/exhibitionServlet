package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.yakovenko.controller.util.Constants.*;

public class BoughtTicketsCommand implements Command {

    private ExhibitionService exhibitionService;

    private UserService userService;

    public BoughtTicketsCommand(ExhibitionService exhibitionService, UserService userService) {
        this.exhibitionService = exhibitionService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        User user = userService.findById(userId);

        String boughtTicketIdStr = request.getParameter("boughtTicketId");
        if (boughtTicketIdStr != null) {
            Long ticketId = Long.valueOf(boughtTicketIdStr);
            try {
                userService.buyTicket(user, ticketId);
            } catch (Exception e) {
                return "redirect:/exhibition/" + URL_BUY_TICKET;
            }
        }

        List<Exhibition> exhibitions = exhibitionService.findBoughtTickets(user);

        request.setAttribute("tickets", exhibitions);

        return PAGE_BOUGHT_TICKETS;
    }
}
