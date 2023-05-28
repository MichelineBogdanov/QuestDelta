package ru.javarush.quest.bogdanov.questdelta.services;

import ru.javarush.quest.bogdanov.questdelta.entities.Quest;
import ru.javarush.quest.bogdanov.questdelta.repositories.QuestRepository;
import ru.javarush.quest.bogdanov.questdelta.repositories.UserRepository;

import java.util.List;

public class QuestService {

    private final QuestRepository questRepository;
    private final UserRepository userRepository;

    public QuestService(QuestRepository questRepository, UserRepository userRepository) {
        this.questRepository = questRepository;
        this.userRepository = userRepository;
    }

    public List<Quest> getAll() {
        return questRepository.getAll();
    }

    public Quest getQuestById(Long id) {
        return questRepository.getByID(id);
    }

    public void create(Quest quest) {
    }

    public String getAuthorLogin(Long authorId) {
        return null;
    }
}
