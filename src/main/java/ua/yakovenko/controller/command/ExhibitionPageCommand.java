package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ExhibitionPageCommand implements Command {
    private ExhibitionService exhibitionService;
    private  List<Exhibition> exhibitions;

    public ExhibitionPageCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String exhibitionIdDelete = request.getParameter("exhibitionIdDelete");
        String showroom = request.getParameter("showroom");

        if (exhibitionIdDelete != null) {
            Long idForDelete = Long.valueOf(exhibitionIdDelete);
            exhibitionService.deleteById(idForDelete);
        }

        if (showroom == null || showroom.equals("")) {
            exhibitions = exhibitionService.findAll();
        } else {
            exhibitions = exhibitionService.findByShowroom(showroom);
        }

        if (exhibitions != null) {
            request.setAttribute("exhibitions", exhibitions);
        }

        User currentUser = (User) request.getSession().getAttribute("user");

        request.setAttribute("currentUser", currentUser);

        return "/WEB-INF/user/pages/exhibitionPage.jsp";
    }
}
