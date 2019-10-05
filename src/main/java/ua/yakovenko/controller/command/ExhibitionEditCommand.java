package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class ExhibitionEditCommand implements Command {

    private ExhibitionService exhibitionService;

    public ExhibitionEditCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String showroom = request.getParameter("showroom");
            String description = request.getParameter("description");
            String priceStr = request.getParameter("price");
            String dateStr = request.getParameter("date");

            Long exhibitionId = Long.valueOf(request.getParameter("exhibitionId"));

            if (CommandUtility.correctInput(name, showroom, description, priceStr, dateStr)) {
                Long price = Long.valueOf(priceStr);
                Date date = Date.valueOf(dateStr);

                User author = (User) request.getSession().getAttribute("user");
                Exhibition exhibition = Exhibition.builder()
                        .id(exhibitionId)
                        .name(name)
                        .showroom(showroom)
                        .description(description)
                        .author(author)
                        .price(price)
                        .date(date)
                        .build();

                exhibitionService.update(exhibition);
            }

            Exhibition currentExhibition = exhibitionService.findById(exhibitionId);
            if (currentExhibition != null) {
                request.setAttribute("exhibition", currentExhibition);
            }
        } catch (Exception e) {
            request.setAttribute("error", "You cannot edit this exhibition");
        }

        return "/WEB-INF/admin/pages/exhibitionEdit.jsp";
    }
}
