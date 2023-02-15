package ru.javarush.quest.bogdanov.questdelta.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.javarush.quest.bogdanov.questdelta.entities.Answer;

import java.util.List;

public class AnswerRepository extends BaseRepository<Answer> {

    public AnswerRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Answer.class);
    }

    public List<Answer> getAnswersByQuestionId(Long id) {
        Session session = getSessionCreator().getSession();
        Transaction transaction = session.beginTransaction();
        List<Answer> result = null;
        try {
            Query<Answer> query = session.createQuery("select a from Answer a where a.question.id = :id", Answer.class);
            query.setParameter("id", id);
            result = query.list();
            transaction.commit();
        } catch (Exception e) {
            getSessionCreator().getSession().getTransaction().rollback();
        }
        return result;
    }

}
