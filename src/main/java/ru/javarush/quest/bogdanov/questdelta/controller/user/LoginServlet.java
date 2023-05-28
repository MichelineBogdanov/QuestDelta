package ru.javarush.quest.bogdanov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.javarush.quest.bogdanov.questdelta.config.Configuration;
import ru.javarush.quest.bogdanov.questdelta.entities.User;
import ru.javarush.quest.bogdanov.questdelta.services.UserService;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_ERROR;
import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_ID;
import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_USER;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_LOGIN;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_USERS;

@WebServlet(name = "LoginServlet", value = GO_LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = Configuration.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User foundUser = userService.find(login, password);
        if (foundUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute(ATTRIBUTE_USER, foundUser);
            session.setAttribute(ATTRIBUTE_ID, foundUser.getId());
            response.sendRedirect(GO_USERS);
        } else {
            request.setAttribute(ATTRIBUTE_ERROR, "User not found");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }
}
