package ru.javarush.quest.bogdanov.questdelta.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question", schema = "data_storage")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answerList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "quest")
    private Quest quest;

    @ManyToOne
    @JoinColumn(name = "correct_question")
    private Question correctQuestion;

    @ManyToOne
    @JoinColumn(name = "incorrect_question")
    private Question incorrectQuestion;

    @Column(name = "text")
    private String text;

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Question getCorrectQuestion() {
        return correctQuestion;
    }

    public void setCorrectQuestion(Question correctQuestion) {
        this.correctQuestion = correctQuestion;
    }

    public Question getIncorrectQuestion() {
        return incorrectQuestion;
    }

    public void setIncorrectQuestion(Question incorrectQuestion) {
        this.incorrectQuestion = incorrectQuestion;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
