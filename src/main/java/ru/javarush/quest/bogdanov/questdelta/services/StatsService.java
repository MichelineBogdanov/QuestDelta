package ru.javarush.quest.bogdanov.questdelta.services;

import ru.javarush.quest.bogdanov.questdelta.entities.Game;

import java.util.List;

public class StatsService {

    private final GameService gameService;
    private final UserService userService;
    private final QuestService questService;

    public StatsService(GameService gameService, UserService userService, QuestService questService) {
        this.gameService = gameService;
        this.userService = userService;
        this.questService = questService;
    }

    public String getGamesStats() {
        return "coming soon";
    }

    public String getUserLogin(Game game) {
        return null;
    }

    public String getQuestName(Game game) {
        return null;
    }

    private String getString(List<Game> games) {
        /*int wins = games.stream()
                .filter(game -> game.gameState == GameState.WIN)
                .toList()
                .size();
        int looses = games.stream()
                .filter(game -> game.gameState == GameState.LOSE)
                .toList()
                .size();
        return "Всего игр сыграно: " + games.size() + ", из них выиграно: " + wins + ", проиграно: " + looses;*/
        return "coming soon";
    }
}
