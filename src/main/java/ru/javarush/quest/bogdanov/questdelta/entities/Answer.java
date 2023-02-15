package ru.javarush.quest.bogdanov.questdelta.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "answer", schema = "data_storage")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;

    @Column(name = "correct", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean correct;

    @Column(name = "text")
    private String text;

    public Answer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", correct=" + correct +
                ", text='" + text + '\'' +
                '}';
    }
}
