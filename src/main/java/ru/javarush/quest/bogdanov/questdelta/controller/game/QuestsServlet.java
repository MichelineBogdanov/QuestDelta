package ru.javarush.quest.bogdanov.questdelta.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.bogdanov.questdelta.config.Configuration;
import ru.javarush.quest.bogdanov.questdelta.entities.Quest;
import ru.javarush.quest.bogdanov.questdelta.services.QuestService;
import ru.javarush.quest.bogdanov.questdelta.utils.Attributes;

import java.io.IOException;
import java.util.List;

import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_QUESTS;

@WebServlet(name = "QuestsServlet", value = GO_QUESTS)
public class QuestsServlet extends HttpServlet {

    private final QuestService questService = Configuration.QUEST_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Quest> all = questService.getAll();
        request.setAttribute(Attributes.ATTRIBUTE_ALL, all);
        request.getRequestDispatcher("WEB-INF/quests.jsp").forward(request, response);
    }
}
