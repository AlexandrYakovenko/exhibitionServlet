package ua.yakovenko.controller.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

import static ua.yakovenko.controller.util.Constants.*;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent){}

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession()
                    .getServletContext()
                        .getAttribute(LOGGED_USERS);

        String username = (String) httpSessionEvent
                .getSession()
                    .getAttribute("username");

        loggedUsers.remove(username);

        httpSessionEvent.getSession().setAttribute(LOGGED_USERS, loggedUsers);
    }
}