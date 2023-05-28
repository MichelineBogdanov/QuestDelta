package ru.javarush.quest.bogdanov.questdelta.controller.user;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.bogdanov.questdelta.config.Configuration;
import ru.javarush.quest.bogdanov.questdelta.entities.Role;
import ru.javarush.quest.bogdanov.questdelta.entities.User;
import ru.javarush.quest.bogdanov.questdelta.services.UserService;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_ERROR;
import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_ROLES;
import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_USER;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_PROFILE;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_SIGNUP;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_USER;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_USERS;

@WebServlet(name = "UserServlet", value = {GO_USER, GO_PROFILE, GO_SIGNUP})
public class UserServlet extends HttpServlet {

    private final UserService userService = Configuration.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initServletContext();
        User user = userService.getUser(getUserId(request));
        if (user != null) {
            request.setAttribute(ATTRIBUTE_USER, user);
        }
        request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId = getUserId(request);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Role select = Role.valueOf(request.getParameter("select"));
        if (postUser(request, userId, login, password, select)) {
            response.sendRedirect(GO_USERS);
        } else {
            request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);
        }
    }

    private boolean postUser(HttpServletRequest request, long id, String login, String password, Role select) {
        User user = userService.getUser(getUserId(request));
        boolean userCheck;
        userCheck = user != null;
        if (!userCheck && request.getParameter("create") != null) {
            if (!userService.create(login, password, select)) {
                request.setAttribute(ATTRIBUTE_ERROR, "This login is present");
                return false;
            }
        } else if (userCheck && request.getParameter("delete") != null) {
            if (!userService.delete(id)) {
                request.setAttribute(ATTRIBUTE_ERROR, "This login is present");
                return false;
            }
        } else if (userCheck && request.getParameter("update") != null) {
            if(!userService.update(id, login, password, select)) {
                request.setAttribute(ATTRIBUTE_ERROR, "This user was not found");
                return false;
            }
        }
        return true;
    }

    private Long getUserId(HttpServletRequest request) {
        return request.getParameter("id") != null
                ? Long.parseLong("0" + request.getParameter("id"))
                : 0L;
    }

    private void initServletContext() {
        ServletContext servletContext = getServletContext();
        if (servletContext.getAttribute(ATTRIBUTE_ROLES) == null) {
            servletContext.setAttribute(ATTRIBUTE_ROLES, Role.values());
        }
    }
}
