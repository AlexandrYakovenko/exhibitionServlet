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
        User user = (User) request.getSession().getAttribute("user");
        Long accountMoney = user.getAccountMoney();
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
