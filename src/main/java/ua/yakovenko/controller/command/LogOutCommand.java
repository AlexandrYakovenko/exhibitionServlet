package ua.yakovenko.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.yakovenko.controller.util.Constants.*;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/exhibition/" + URL_INDEX;
    }
}
