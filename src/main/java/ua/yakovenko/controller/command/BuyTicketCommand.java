package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BuyTicketCommand implements Command {
    private static final String BUY_ERROR = "You cannot buy this ticket";

    private ExhibitionService exhibitionService;
    private UserService userService;

    public BuyTicketCommand(ExhibitionService eS, UserService uS) {
        this.exhibitionService = eS;
        this.userService = uS;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        String ticketIdString = request.getParameter("ticketId");
        String boughtTicketIdString = request.getParameter("boughTicketId");

        if (ticketIdString != null) {
            Long ticket = Long.valueOf(ticketIdString);
            request.getSession().setAttribute("ticketId", ticket);
        } else if (boughtTicketIdString != null) {
            Long boughtTicket = Long.valueOf(boughtTicketIdString);
            try {
                userService.buyTicket(user, boughtTicket);
                return "redirect:/exhibition/bought-tickets";
            } catch (Exception e) {
                request.setAttribute("error", BUY_ERROR);
            }
        }

        Long ticketId = (Long) request.getSession().getAttribute("ticketId");
        Exhibition exhibition = exhibitionService.findById(ticketId);

        request.setAttribute("exhibition", exhibition);

        return "/WEB-INF/user/pages/buyTicket.jsp";
    }
}
