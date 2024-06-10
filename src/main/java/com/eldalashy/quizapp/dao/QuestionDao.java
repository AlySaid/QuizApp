package com.eldalashy.quizapp.dao;

import com.eldalashy.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "Select * from question q where q.category = :cat  Order by Random() LIMIT :numQ " , nativeQuery = true)
    List<Question> findRandomQuestionByCategory(int numQ, String cat);
}
