package ua.yakovenko.controller.command;

import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class ExhibitionAddController implements Command {
    private ExhibitionService exhibitionService;

    public ExhibitionAddController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/admin/pages/exhibitionAdd.jsp";
    }
}
