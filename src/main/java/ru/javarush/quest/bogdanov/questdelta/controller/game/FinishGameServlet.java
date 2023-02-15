package ru.javarush.quest.bogdanov.questdelta.controller.game;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.javarush.quest.bogdanov.questdelta.utils.Go;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.GAME;
import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.QUESTION;

@WebServlet(name = "FinishGameServlet", value = Go.FINISH)
public class FinishGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(GAME);
        session.removeAttribute(QUESTION);
        response.sendRedirect(Go.STATS);
    }
}
