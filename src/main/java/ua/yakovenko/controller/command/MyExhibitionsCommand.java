package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.yakovenko.controller.util.Constants.*;

public class MyExhibitionsCommand implements Command {

    private ExhibitionService exhibitionService;

    public MyExhibitionsCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User author = (User) request.getSession().getAttribute("user");

        List<Exhibition> exhibitions = exhibitionService.findByAuthor(author);

        request.setAttribute("exhibitions", exhibitions);
        request.setAttribute("user", author);

        return PAGE_MY_EXHIBITIONS;
    }
}