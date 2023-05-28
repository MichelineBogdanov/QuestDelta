package ru.javarush.quest.bogdanov.questdelta.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.javarush.quest.bogdanov.questdelta.entities.Game;
import ru.javarush.quest.bogdanov.questdelta.repositories.GameRepository;

import java.util.List;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAll() {
        return gameRepository.getAll();
    }

    public void create(Game game) {
        Session session = gameRepository.getSessionCreator().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            gameRepository.create(game);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void update(Game game) {
        Session session = gameRepository.getSessionCreator().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            gameRepository.update(game);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

}
