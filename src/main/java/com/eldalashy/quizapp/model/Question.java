package com.eldalashy.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;


@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category")
    private String category;

    @Column(name = "diffecultylevel")
    private String difficultyLevel;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "right_answer")
    private String rightAnswer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(category, question.category) && Objects.equals(difficultyLevel, question.difficultyLevel) && Objects.equals(option1, question.option1) && Objects.equals(option2, question.option2) && Objects.equals(option3, question.option3) && Objects.equals(option4, question.option4) && Objects.equals(questionTitle, question.questionTitle) && Objects.equals(rightAnswer, question.rightAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, difficultyLevel, option1, option2, option3, option4, questionTitle, rightAnswer);
    }
}