package ru.javarush.quest.bogdanov.questdelta.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ru.javarush.quest.bogdanov.questdelta.entities.*;

import java.util.Properties;

public class SessionCreator implements AutoCloseable {

    public static SessionCreator instance;
    private final SessionFactory sessionFactory;

    private SessionCreator() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/quest");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "postgre");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.SHOW_SQL, "false");
        //properties.put(Environment.HBM2DDL_AUTO, "validate");

        sessionFactory = new Configuration()
                .addAnnotatedClass(Answer.class)
                .addAnnotatedClass(Game.class)
                .addAnnotatedClass(GameState.class)
                .addAnnotatedClass(Quest.class)
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(User.class)
                .setProperties(properties)
                .buildSessionFactory()
        ;
    }

    public static SessionCreator getSessionCreator() {
        if (instance == null) {
            instance = new SessionCreator();
        }
        return instance;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
