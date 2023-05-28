package ru.javarush.quest.bogdanov.questdelta.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AnswerRepositoryTest {

    static AnswerRepository answerRepository;

    @Disabled
    @BeforeAll
    public static void init() {
        //answerRepository = AnswerRepository.getInstance();
    }
    @Disabled
    @Test
    void getAnswersByQuestionIdAndGetItCorrectlySize() {
        /*List<Answer> answersByQuestionId = answerRepository.findAnswersByQuestionId(2L);
        int actualSize = answersByQuestionId.size();
        int expectedSize = 2;
        List<String> actualTexts = answersByQuestionId.stream()
                .map(answer -> answer.getText())
                .toList();
        List<String> expectedTexts = Arrays.asList("тест1", "тест2");
        assertEquals(expectedSize, actualSize);
        assertEquals(expectedTexts, actualTexts);*/
    }

}