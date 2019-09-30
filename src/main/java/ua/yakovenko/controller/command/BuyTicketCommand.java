package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class BuyTicketCommand implements Command {
    private ExhibitionService exhibitionService;

    public BuyTicketCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
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

        request.setAttribute("exhibition", exhibition);
        
        return "/WEB-INF/user/pages/buyTicket.jsp";
    }
}
