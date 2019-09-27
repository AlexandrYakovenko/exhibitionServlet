package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class ExhibitionEditController implements Command {
    private ExhibitionService exhibitionService;

    public ExhibitionEditController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long exhibitionId = Long.valueOf(request.getParameter("exhibitionId"));
        Exhibition currentExhibition = exhibitionService.findById(exhibitionId);
        //TODO сделать редактирование выставки
        if (currentExhibition != null) {
            request.setAttribute("exhibition", currentExhibition);
        }

        return "/WEB-INF/admin/pages/exhibitionEdit.jsp";
    }
}
