package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.yakovenko.controller.util.Constants.*;

public class BuyTicketCommand implements Command {

    private static final String HAVE_TICKET = "haveTicket";

    private static final String HAVE_TICKET_ERROR = "You have already bought this ticket.";

    private ExhibitionService exhibitionService;

    private UserService userService;

    public BuyTicketCommand(ExhibitionService exhibitionService, UserService userService) {
        this.exhibitionService = exhibitionService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        User user = userService.findById(userId);
        request.setAttribute(CURRENT_USER, user);

        String exhibitionIdStr = request.getParameter(EXHIBITION_ID);
        if (exhibitionIdStr != null) {
            Long exhibitionId = Long.valueOf(exhibitionIdStr);
            request.getSession().setAttribute(EXHIBITION_ID, exhibitionId);
        }

        Exhibition exhibition = null;
        Object exhibitionIdObj = request.getSession().getAttribute(EXHIBITION_ID);
        if (exhibitionIdObj != null) {
            Long exhibitionId = (Long) exhibitionIdObj;
            exhibition = exhibitionService.findById(exhibitionId);
            request.setAttribute("exhibition", exhibition);
        }

        List<Exhibition> exhibitions = exhibitionService.findBoughtTickets(user);
        if (exhibitions != null) {
            if (exhibitions.contains(exhibition)) {
                request.setAttribute(HAVE_TICKET, HAVE_TICKET_ERROR);
            }
        }

        return PAGE_BUY_TICKET;
    }
}
