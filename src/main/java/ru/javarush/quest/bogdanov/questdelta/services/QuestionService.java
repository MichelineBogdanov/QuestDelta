package ru.javarush.quest.bogdanov.questdelta.services;

import ru.javarush.quest.bogdanov.questdelta.entities.Answer;
import ru.javarush.quest.bogdanov.questdelta.entities.Question;
import ru.javarush.quest.bogdanov.questdelta.repositories.AnswerRepository;
import ru.javarush.quest.bogdanov.questdelta.repositories.QuestionRepository;

import java.util.List;

public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Question getQuestionById(Long id) {
        return null;
    }

    public List<Question> getAll() {
        return null;
    }

    public List<Question> getQuestionsByQuestId(Long id) {
        return questionRepository.getQuestionsByQuestId(id);
    }

    public void create(Question question) {
    }

    public Question getNextQuestionByAnswer(Long questionId, Long answerId) {
        Answer answer = answerRepository.getByID(answerId);
        return questionRepository.getNextQuestionByAnswer(questionId, answer.getCorrect());
    }

    public Question firstQuestionId(Long questId) {
        return getQuestionsByQuestId(questId).get(0);
    }

}
