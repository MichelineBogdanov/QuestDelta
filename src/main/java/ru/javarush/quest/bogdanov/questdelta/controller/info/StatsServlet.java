package ru.javarush.quest.bogdanov.questdelta.controller.info;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.bogdanov.questdelta.config.Configuration;
import ru.javarush.quest.bogdanov.questdelta.entities.Game;
import ru.javarush.quest.bogdanov.questdelta.services.GameService;
import ru.javarush.quest.bogdanov.questdelta.services.StatsService;
import ru.javarush.quest.bogdanov.questdelta.utils.Attributes;
import ru.javarush.quest.bogdanov.questdelta.utils.Go;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StatsServlet", value = Go.STATS)
public class StatsServlet extends HttpServlet {

    private final GameService gameService = Configuration.GAME_SERVICE;
    private final StatsService statsService = Configuration.STATS_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Game> all = gameService.getAll();
        String gamesStats = statsService.getGamesStats();
        request.setAttribute(Attributes.ALL, all);
        request.setAttribute(Attributes.GAMES_STATS, gamesStats);
        request.getRequestDispatcher("WEB-INF/stats.jsp").forward(request, response);
    }
}
