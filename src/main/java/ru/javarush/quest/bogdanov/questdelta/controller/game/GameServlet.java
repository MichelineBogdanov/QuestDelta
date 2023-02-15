package ru.javarush.quest.bogdanov.questdelta.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.bogdanov.questdelta.config.Configuration;
import ru.javarush.quest.bogdanov.questdelta.entities.*;
import ru.javarush.quest.bogdanov.questdelta.services.AnswerService;
import ru.javarush.quest.bogdanov.questdelta.services.GameService;
import ru.javarush.quest.bogdanov.questdelta.services.QuestService;
import ru.javarush.quest.bogdanov.questdelta.services.QuestionService;
import ru.javarush.quest.bogdanov.questdelta.utils.Go;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Attributes.*;

@WebServlet(name = "GameServlet", value = Go.GAME)
public class GameServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(GameServlet.class);
    private final GameService gameService = Configuration.GAME_SERVICE;
    private final QuestService questService = Configuration.QUEST_SERVICE;
    private final QuestionService questionService = Configuration.QUESTION_SERVICE;
    private final AnswerService answerService = Configuration.ANSWER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute(GAME) == null) {
            init(request, session);
        }
        request.getRequestDispatcher("WEB-INF/game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        long currentQuestionId = Long.parseLong(request.getParameter("currentquestionid"));
        long answerId = Long.parseLong(request.getParameter("answerid"));
        Question nextQuestionByAnswer = questionService.getNextQuestionByAnswer(currentQuestionId, answerId);
        Game game = (Game) session.getAttribute(GAME);
        if (nextQuestionByAnswer.getAnswerList().size() == 0) {
            if (answerService.getAnswer(answerId).getCorrect()) {
                game.setGameState(GameState.WIN);
            } else {
                game.setGameState(GameState.LOSE);
            }
        } else {
            game.setCurrentQuestion(nextQuestionByAnswer);
        }
        gameService.update(game);
        session.setAttribute(QUESTION, nextQuestionByAnswer);
        request.getRequestDispatcher("WEB-INF/game.jsp").forward(request, response);
    }

    private void init(HttpServletRequest request, HttpSession session) {
        long questId = getQuestId(request);
        Quest quest = questService.getQuestById(questId);
        User currentUser = (User) session.getAttribute(USER);
        Question firstQuestion = questionService.firstQuestionId(questId);
        Game game = new Game();
        game.setCurrentQuestion(firstQuestion);
        game.setGameState(GameState.STARTED);
        game.setUser(currentUser);
        game.setQuest(quest);
        gameService.create(game);
        //log.info("game {} initialized, with {} quest, by user {}", game.getId(), questId, currentUserId);
        session.setAttribute(GAME, game);
        session.setAttribute(QUESTION, firstQuestion);
    }

    private Long getQuestId(HttpServletRequest request) {
        return request.getParameter("questid") != null
                ? Long.parseLong("0" + request.getParameter("questid"))
                : 1L;
    }
}
