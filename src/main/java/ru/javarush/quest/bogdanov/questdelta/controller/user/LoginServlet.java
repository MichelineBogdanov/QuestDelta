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
import ru.javarush.quest.bogdanov.questdelta.utils.Go;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.*;

@WebServlet(name = "LoginServlet", value = Go.LOGIN)
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
            session.setAttribute(USER, foundUser);
            session.setAttribute(ID, foundUser.getId());
            response.sendRedirect("/users");
        } else {
            request.setAttribute(ERROR, "User not found");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }
}
