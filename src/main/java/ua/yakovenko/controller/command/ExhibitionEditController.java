package ua.yakovenko.controller.command;

import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class ExhibitionEditController implements Command {
    private ExhibitionService exhibitionService;

    public ExhibitionEditController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/admin/pages/exhibitionEdit.jsp";
    }
}
