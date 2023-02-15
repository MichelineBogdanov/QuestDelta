package ru.javarush.quest.bogdanov.questdelta.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.javarush.quest.bogdanov.questdelta.entities.Question;

import java.util.List;

public class QuestionRepository extends BaseRepository<Question> {

    public QuestionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Question.class);
    }

    public List<Question> getQuestionsByQuestId(Long id) {
        Session session = getSessionCreator().getSession();
        Transaction transaction = session.beginTransaction();
        List<Question> result = null;
        try {
            Query<Question> query = session.createQuery("select q from Question q where q.quest.id = :id", Question.class);
            query.setParameter("id", id);
            result = query.list();
            transaction.commit();
        } catch (Exception e) {
            getSessionCreator().getSession().getTransaction().rollback();
        }
        return result;
    }

    public Question getNextQuestionByAnswer(Long questionId, Boolean isCorrect) {
        Session session = getSessionCreator().getSession();
        Transaction transaction = session.beginTransaction();
        Question nextQuestion = null;
        try {
            Query<Question> query;
            if (isCorrect) {
                query = session.createQuery("select q.correctQuestion from Question q where q.id = :id", Question.class);
            } else {
                query = session.createQuery("select q.incorrectQuestion from Question q where q.id = :id", Question.class);
            }
            query.setParameter("id", questionId);
            nextQuestion = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            getSessionCreator().getSession().getTransaction().rollback();
        }
        return nextQuestion;
    }

}
