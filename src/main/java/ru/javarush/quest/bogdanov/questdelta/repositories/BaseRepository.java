package ru.javarush.quest.bogdanov.questdelta.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseRepository<T> implements Repository<T> {

    private final SessionCreator sessionCreator;
    private final Class<T> aClass;

    public BaseRepository(SessionCreator sessionCreator, Class<T> aClass) {
        this.sessionCreator = sessionCreator;
        this.aClass = aClass;
    }

    @Override
    public List<T> getAll() {
        Session session = sessionCreator.getSession();
        Transaction transaction = session.beginTransaction();
        List<T> result = null;
        try {
            Query<T> queryAll = session.createQuery("select e from " + aClass.getSimpleName() + " e", aClass);
            result = queryAll.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println(result);
        return result;
    }

    @Override
    public T getByID(Long id) {
        Session session = sessionCreator.getSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            result = session.get(aClass, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }

    @Override
    public void create(T entity) {
        Session session = sessionCreator.getSession();
        session.save(entity);
    }

    @Override
    public void update(T entity) {
        Session session = sessionCreator.getSession();
        session.merge(entity);

    }

    @Override
    public void delete(Long id) {
        Session session = sessionCreator.getSession();
        T entity = getByID(id);
        session.remove(entity);
    }

    @Override
    public T find(T entity) {
        Session session = sessionCreator.getSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(aClass);
            Root<T> root = query.from(aClass);
            List<Predicate> predicates = new ArrayList<>();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    String name = field.getName();
                    Object value = field.get(entity);
                    if (Objects.nonNull(value) && field.getType() == String.class) {
                        Predicate condition = criteriaBuilder.equal(root.get(name), value);
                        predicates.add(condition);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            query = query.where(predicates.toArray(Predicate[]::new));
            result = session.createQuery(query).list().get(0);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }

    public SessionCreator getSessionCreator() {
        return sessionCreator;
    }
}
