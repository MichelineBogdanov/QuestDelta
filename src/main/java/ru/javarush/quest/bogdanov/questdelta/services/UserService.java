package ru.javarush.quest.bogdanov.questdelta.services;

import org.hibernate.Transaction;
import ru.javarush.quest.bogdanov.questdelta.entities.Role;
import ru.javarush.quest.bogdanov.questdelta.entities.User;
import ru.javarush.quest.bogdanov.questdelta.repositories.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getUser(Long id) {
        return userRepository.getByID(id);
    }

    public boolean create(String login, String password, Role role) {
        Transaction transaction = userRepository.getSessionCreator().getSession().beginTransaction();
        try {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            userRepository.create(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public boolean update(Long id, String login, String password, Role role) {
        Transaction transaction = userRepository.getSessionCreator().getSession().beginTransaction();
        try {
            User user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            userRepository.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public boolean delete(Long id) {
        Transaction transaction = userRepository.getSessionCreator().getSession().beginTransaction();
        try {
            userRepository.delete(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public User find(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userRepository.find(user);
    }
}
