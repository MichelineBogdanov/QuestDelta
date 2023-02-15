package ru.javarush.quest.bogdanov.questdelta.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {

    static UserRepository userRepository;
    @BeforeAll
    public static void init() {
        //userRepository = new UserRepository();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findUserFromMapWithLoginAndPasswordAndFound() {
        /*User pattern = new User("Misha", "1111");
        Optional<User> actual = userRepository.find(pattern);
        assertTrue(actual.isPresent());*/
    }

    @Test
    void findUserFromMapWithLoginAndPasswordAndNotFound() {
        /*User pattern = new User("Misha1", "1112");
        Optional<User> actual = userRepository.find(pattern);
        assertTrue(actual.isEmpty());*/
    }
}