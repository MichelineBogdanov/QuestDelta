package ru.javarush.quest.bogdanov.questdelta.services;

import ru.javarush.quest.bogdanov.questdelta.entities.Answer;
import ru.javarush.quest.bogdanov.questdelta.repositories.AnswerRepository;

import java.util.List;

public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAll() {
        return answerRepository.getAll();
    }

    public Answer getAnswer(Long id) {
        return answerRepository.getByID(id);
    }

    public List<Answer> getAnswersByQuestionId(Long id) {
        return answerRepository.getAnswersByQuestionId(id);
    }

    public void create(Answer answer) {
    }
}
