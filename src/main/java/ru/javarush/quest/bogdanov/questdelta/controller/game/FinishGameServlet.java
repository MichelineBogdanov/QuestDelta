package ru.javarush.quest.bogdanov.questdelta.controller.game;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_GAME;
import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.ATTRIBUTE_QUESTION;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_FINISH;
import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_STATS;

@WebServlet(name = "FinishGameServlet", value = GO_FINISH)
public class FinishGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(ATTRIBUTE_GAME);
        session.removeAttribute(ATTRIBUTE_QUESTION);
        response.sendRedirect(GO_STATS);
    }
}
