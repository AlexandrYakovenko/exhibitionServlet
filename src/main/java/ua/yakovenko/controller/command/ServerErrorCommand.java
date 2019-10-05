package ua.yakovenko.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ServerErrorCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/serverErrorPage.jsp";
    }
}
