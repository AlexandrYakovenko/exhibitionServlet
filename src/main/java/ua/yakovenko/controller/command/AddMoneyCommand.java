package ua.yakovenko.controller.command;

import ua.yakovenko.controller.util.CommandUtility;
import ua.yakovenko.model.entity.User;
import ua.yakovenko.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.yakovenko.controller.util.Constants.*;

public class AddMoneyCommand implements Command {

    private UserService userService;

    public AddMoneyCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        User user = userService.findById(userId);
        request.setAttribute(CURRENT_USER, user);

        String moneyString = request.getParameter(MONEY);
        Long accountMoney = user.getAccountMoney();
        if (moneyString != null) {
            Long money = Long.valueOf(moneyString);
            Long value = accountMoney + money;
            userService.updateBalance(user, value);

            CommandUtility.setUser(request, user);
            return "redirect:/exhibition/" + URL_BUY_TICKET;
        }

        return PAGE_ADD_MONEY;
    }
}
