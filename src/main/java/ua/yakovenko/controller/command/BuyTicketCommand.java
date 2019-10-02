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
        String exhibitionIdStr = request.getParameter("exhibitionId");

        if (exhibitionIdStr != null) {
            Long exhibitionId = Long.valueOf(exhibitionIdStr);
            request.setAttribute("exhibitionId", exhibitionId);
            Exhibition exhibition = exhibitionService.findById(exhibitionId);
            request.setAttribute("exhibition", exhibition);
        }

        Object userIdObj = request.getSession().getAttribute("userId");

        if (userIdObj != null) {
            Long userId = (Long) userIdObj;
            User currentUser = userService.findById(userId);
            request.setAttribute("currentUser", currentUser);
        }
        
        return "/WEB-INF/user/pages/buyTicket.jsp";
    }
}
