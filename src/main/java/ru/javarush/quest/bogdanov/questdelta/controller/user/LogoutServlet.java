package ru.javarush.quest.bogdanov.questdelta.controller.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_LOGIN;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_LOGOUT;

@WebServlet(name = "LogoutServlet", value = GO_LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(GO_LOGIN);
    }
}
