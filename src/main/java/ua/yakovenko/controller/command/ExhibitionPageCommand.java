package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ExhibitionPageCommand implements Command {
    private ExhibitionService exhibitionService;
    private  List<Exhibition> exhibitions = null;

    public ExhibitionPageCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String exhibitionIdDelete = request.getParameter("exhibitionIdDelete");
        if (exhibitionIdDelete != null) {
            Long idForDelete = Long.valueOf(exhibitionIdDelete);
            exhibitionService.deleteById(idForDelete);
        }

        String showroom = request.getParameter("showroom");
        if (showroom == null || showroom.equals("")) {
            CommandUtility.showPagination(request, exhibitionService);
        } else {
            exhibitions = exhibitionService.findByShowroom(showroom);
            request.setAttribute("exhibitionList", exhibitions);
        }

        User currentUser = (User) request.getSession().getAttribute("user");
        request.setAttribute("currentUser", currentUser);

        return "/WEB-INF/user/pages/exhibitionPage.jsp";
    }
}
