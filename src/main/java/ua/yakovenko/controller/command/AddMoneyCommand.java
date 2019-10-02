package ua.yakovenko.controller.command;

import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AddMoneyCommand implements Command {
    private UserService userService;

    public AddMoneyCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        User user = userService.findById(userId);
        Long accountMoney = user.getAccountMoney();
        request.setAttribute("currentUser", user);

        String moneyString = request.getParameter("money");

        if (moneyString != null) {
            Long money = Long.valueOf(moneyString);
            user.setAccountMoney(accountMoney + money);
            userService.update(user);
            return "redirect:/exhibition/buy-ticket";
        }

        return "/WEB-INF/user/pages/addMoney.jsp";
    }
}
