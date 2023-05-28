package ru.javarush.quest.bogdanov.questdelta.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.bogdanov.questdelta.entities.Role;
import ru.javarush.quest.bogdanov.questdelta.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.javarush.quest.bogdanov.questdelta.utils.Go.*;

@WebFilter({GO_ROOT, GO_USERS, GO_LOGIN, GO_SIGNUP, GO_NOT_IMPLEMENTED, GO_LOGOUT, GO_PROFILE, GO_QUESTS, GO_STATS, GO_SIGNUP_INFO, GO_GAME, GO_USER, GO_FINISH})
public class RoleSelector implements Filter {

    private final Map<Role, List<String>> uriMap = Map.of(
            Role.GUEST, List.of(GO_ROOT, GO_USERS, GO_LOGIN, GO_SIGNUP, GO_NOT_IMPLEMENTED, GO_LOGOUT, GO_PROFILE, GO_QUESTS, GO_STATS, GO_SIGNUP_INFO),
            Role.USER, List.of(GO_ROOT, GO_USERS, GO_LOGIN, GO_SIGNUP, GO_NOT_IMPLEMENTED, GO_LOGOUT, GO_PROFILE, GO_QUESTS, GO_STATS, GO_GAME, GO_FINISH),
            Role.ADMIN, List.of(GO_ROOT, GO_USERS, GO_LOGIN, GO_SIGNUP, GO_NOT_IMPLEMENTED, GO_LOGOUT, GO_PROFILE, GO_QUESTS, GO_STATS, GO_GAME, GO_USER, GO_FINISH)
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = getCommand(request);
        Object rawUser = request.getSession().getAttribute("user");
        Role role = Objects.isNull(rawUser)
                ? Role.GUEST
                : ((User) rawUser).getRole();
        if (uriMap.get(role).contains(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(GO_SIGNUP_INFO);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private String getCommand(HttpServletRequest request) {
        //TODO изменить реализацию
        String uri = request.getRequestURI();
        Matcher matcher = Pattern.compile("/[a-z]*").matcher(uri);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new UnsupportedOperationException("incorrect uri" + uri);
        }
    }
}
