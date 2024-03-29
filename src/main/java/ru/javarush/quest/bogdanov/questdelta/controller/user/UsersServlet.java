package ru.javarush.quest.bogdanov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.bogdanov.questdelta.config.Configuration;
import ru.javarush.quest.bogdanov.questdelta.entities.User;
import ru.javarush.quest.bogdanov.questdelta.services.StatsService;
import ru.javarush.quest.bogdanov.questdelta.services.UserService;

import java.io.IOException;
import java.util.List;

import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_ALL;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_USERS;

@WebServlet(name = "UsersServlet", value = GO_USERS)
public class UsersServlet extends HttpServlet {

    private final UserService userService = Configuration.USER_SERVICE;
    private final StatsService statsService = Configuration.STATS_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> all = userService.getAll();
        request.setAttribute(ATTRIBUTE_ALL, all);
        request.getRequestDispatcher("WEB-INF/users.jsp").forward(request, response);
    }
}
