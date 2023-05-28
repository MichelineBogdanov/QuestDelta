package ru.javarush.quest.bogdanov.questdelta.config;

import ru.javarush.quest.bogdanov.questdelta.repositories.*;
import ru.javarush.quest.bogdanov.questdelta.services.*;

public class Configuration {

    public static final SessionCreator SESSION_CREATOR = SessionCreator.getSessionCreator();
    public static final AnswerRepository ANSWER_REPOSITORY = new AnswerRepository(SESSION_CREATOR);
    public static final GameRepository GAME_REPOSITORY = new GameRepository(SESSION_CREATOR);
    public static final QuestionRepository QUESTION_REPOSITORY = new QuestionRepository(SESSION_CREATOR);
    public static final QuestRepository QUEST_REPOSITORY = new QuestRepository(SESSION_CREATOR);
    public static final UserRepository USER_REPOSITORY = new UserRepository(SESSION_CREATOR);
    public static final AnswerService ANSWER_SERVICE = new AnswerService(ANSWER_REPOSITORY);
    public static final GameService GAME_SERVICE = new GameService(GAME_REPOSITORY);
    public static final QuestionService QUESTION_SERVICE = new QuestionService(QUESTION_REPOSITORY, ANSWER_REPOSITORY);
    public static final QuestService QUEST_SERVICE = new QuestService(QUEST_REPOSITORY, USER_REPOSITORY);
    public static final UserService USER_SERVICE = new UserService(USER_REPOSITORY);
    public static final StatsService STATS_SERVICE = new StatsService(GAME_SERVICE, USER_SERVICE, QUEST_SERVICE);

}
