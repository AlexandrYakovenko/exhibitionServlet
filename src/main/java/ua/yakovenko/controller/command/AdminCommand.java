package ua.yakovenko.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.yakovenko.controller.util.Constants.*;

public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        return PAGE_ADMIN;
    }
}
