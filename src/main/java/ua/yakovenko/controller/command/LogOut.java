package ua.yakovenko.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
      /*метод .invalidate() заменяет метод коммандУтилити:
      CommandUtility.deleteUserFromContextAndSession(request);*/
        request.getSession().invalidate();
        return "redirect:/index.jsp";
    }
}
