package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ExhibitionPageController implements Command {
    private ExhibitionService exhibitionService;
    private  List<Exhibition> exhibitions;

    public ExhibitionPageController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        exhibitions = exhibitionService.findAll();
        System.out.println(">>>>>" + exhibitions);
        if (exhibitions != null) {
            request.setAttribute("exhibitions", exhibitions);
        }

        User currentUser = (User) request.getSession().getAttribute("user");

        request.setAttribute("currentUser", currentUser);

        return "/WEB-INF/user/pages/exhibitionPage.jsp";
    }
}
