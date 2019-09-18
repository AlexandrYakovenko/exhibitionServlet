package ua.yakovenko.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
//        Role role = (Role) request.getSession().getAttribute("role");
//        if (role != null && role.equals(Role.ADMIN))
        return "/WEB-INF/admin/adminbasis.jsp";
//        else return "redirect:/";
    }
}
