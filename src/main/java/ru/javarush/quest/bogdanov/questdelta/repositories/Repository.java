package ru.javarush.quest.bogdanov.questdelta.repositories;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();

    T getByID(Long id);

    void create(T entity);

    void update(T entity);

    void delete(Long id);

    T find(T pattern);

}
