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

@WebFilter({"/", "/users", "/login", "/signup","/notimplemented", "/logout", "/profile", "/quests", "/stats", "/sugnupinfo", "/game", "/user", "/finish"})
public class RoleSelector implements Filter {

    private final Map<Role, List<String>> uriMap = Map.of(
            Role.GUEST, List.of("/", "/users", "/login", "/signup", "/notimplemented", "/logout", "/profile", "/quests", "/stats", "/sugnupinfo"),
            Role.USER, List.of("/", "/users", "/login", "/signup", "/notimplemented", "/logout", "/profile", "/quests", "/stats", "/game", "/finish"),
            Role.ADMIN, List.of("/", "/users", "/login", "/signup", "/notimplemented","/logout",  "/profile", "/quests", "/stats", "/game", "/user", "/finish")
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
            response.sendRedirect("/signupinfo");
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
