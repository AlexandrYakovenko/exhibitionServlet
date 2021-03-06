package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;
import ua.yakovenko.controller.util.CommandUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.yakovenko.controller.util.Constants.*;

public class ExhibitionPageCommand implements Command {

    private ExhibitionService exhibitionService;

    public ExhibitionPageCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("currentPage", 1);

        String exhibitionIdDelete = request.getParameter(EXHIBITION_ID);
        if (exhibitionIdDelete != null) {
            Long idForDelete = Long.valueOf(exhibitionIdDelete);
            exhibitionService.deleteById(idForDelete);
        }

        String showroom = request.getParameter("showroom");
        if (showroom == null || showroom.equals("")) {
            CommandUtility.showPagination(request, exhibitionService);
        } else {
            List<Exhibition> exhibitions = exhibitionService.findByShowroom(showroom);
            request.setAttribute("exhibitionList", exhibitions);
        }

        User currentUser = (User) request.getSession().getAttribute("user");
        request.setAttribute(CURRENT_USER, currentUser);

        return PAGE_EXHIBITION_PAGE;
    }
}
