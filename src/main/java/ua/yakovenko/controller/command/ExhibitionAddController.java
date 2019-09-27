package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class ExhibitionAddController implements Command {
    private ExhibitionService exhibitionService;

    public ExhibitionAddController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String showroom = request.getParameter("showroom");
        String description = request.getParameter("description");
        String priceString = request.getParameter("price");
        String dateString = request.getParameter("date");
        Long price = null;
        Date date = null;

        //TODO сделать проверку водимых данных
        if (priceString != null && dateString != null) {
            price = Long.valueOf(priceString);
            date = Date.valueOf(dateString);
        }

        if (name != null
                && showroom != null
                && description != null
                && price != null
                && date != null
        ) {
            User author = (User) request.getSession().getAttribute("user");

            Exhibition exhibition = Exhibition.builder()
                    .name(name)
                    .showroom(showroom)
                    .description(description)
                    .author(author)
                    .price(price)
                    .date(date)
                    .build();

            try {
                exhibitionService.add(exhibition);
            } catch (Exception e) {
                request.setAttribute("error", "You cannot add this exhibition");
                return "/WEB-INF/admin/pages/exhibitionAdd.jsp";
            }

            return "redirect:/exhibition/exhibitions";
        }

        return "/WEB-INF/admin/pages/exhibitionAdd.jsp";
    }
}
