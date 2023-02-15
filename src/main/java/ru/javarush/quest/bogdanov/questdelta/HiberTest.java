package ru.javarush.quest.bogdanov.questdelta;

import ru.javarush.quest.bogdanov.questdelta.config.Configuration;
import ru.javarush.quest.bogdanov.questdelta.entities.*;
import ru.javarush.quest.bogdanov.questdelta.repositories.*;
import ru.javarush.quest.bogdanov.questdelta.services.QuestionService;

import java.util.List;

public class HiberTest {

    public static void main(String[] args) {

        AnswerRepository answerRepository = Configuration.ANSWER_REPOSITORY;
        List<Answer> all1 = answerRepository.getAll();
        if (all1 == null) {
            System.out.println("no res");
        } else {
            for (Answer answer : all1) {
                System.out.println(answer);
            }
        }
        List<Answer> answersByQuestionId = answerRepository.getAnswersByQuestionId(3L);
        for (Answer answer : answersByQuestionId) {
            System.out.println(answer);
        }

        GameRepository gameRepository = Configuration.GAME_REPOSITORY;
        List<Game> all2 = gameRepository.getAll();
        if (all2 == null) {
            System.out.println("no res");
        } else {
            for (Game game : all2) {
                System.out.println(game);
            }
        }

        QuestRepository questRepository = Configuration.QUEST_REPOSITORY;
        List<Quest> all3 = questRepository.getAll();
        if (all3 == null) {
            System.out.println("no res");
        } else {
            for (Quest quest : all3) {
                System.out.println(quest);
            }
        }
        Quest quest = questRepository.getByID(1L);
        System.out.println(quest);

        UserRepository userRepository = Configuration.USER_REPOSITORY;
        List<User> all4 = userRepository.getAll();
        if (all4 == null) {
            System.out.println("no res");
        } else {
            for (User user : all4) {
                System.out.println(user);
            }
        }
        User user = userRepository.getByID(1L);
        System.out.println(user);

        QuestionRepository questionRepository = Configuration.QUESTION_REPOSITORY;
        List<Question> all5 = questionRepository.getAll();
        if (all5 == null) {
            System.out.println("no res");
        } else {
            for (Question question : all5) {
                System.out.println(question);
            }
        }
        Question question = questionRepository.getByID(1L);
        System.out.println(question);

        QuestionService questionService = Configuration.QUESTION_SERVICE;
        Question nextQuestionByAnswer = questionService.getNextQuestionByAnswer(1L, 1L);
        System.out.println("-------");
        System.out.println(nextQuestionByAnswer);

    }

}
