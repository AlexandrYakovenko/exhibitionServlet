package ua.yakovenko.controller.command;

import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class BoughtTicketsCommand implements Command {
    private ExhibitionService exhibitionService;

    public BoughtTicketsCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {



        return "/WEB-INF/user/pages/boughtTickets.jsp";
    }
}
