package com.eldalashy.quizapp.service;

import com.eldalashy.quizapp.dao.QuestionDao;
import com.eldalashy.quizapp.dao.QuizDao;
import com.eldalashy.quizapp.model.Question;
import com.eldalashy.quizapp.model.QuestionWrapper;
import com.eldalashy.quizapp.model.Quiz;
import com.eldalashy.quizapp.model.Response;
import org.aspectj.weaver.patterns.PointcutRewriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String cat, int numQ, String title) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setNumQ(numQ);

            List<Question> questions = questionDao.findRandomQuestionByCategory(numQ, cat);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("Quiz " + title + " created !!", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error creating Quiz " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer id) {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questionsFromDb = quiz.get().getQuestions();
            List<QuestionWrapper> questionsToUser = new ArrayList<>();
            QuestionWrapper qw;

            for (Question q : questionsFromDb) {
                qw = new QuestionWrapper(q.getId(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getQuestionTitle());
                questionsToUser.add(qw);
            }

            return new ResponseEntity<>(questionsToUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        int score, i;
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> quizQuestions;
            score = 0;
            i = 0;
            if (!quiz.isEmpty()) {
                quizQuestions = quiz.get().getQuestions();
                Iterator<Question> iterator = quizQuestions.iterator();
                while (iterator.hasNext()) {
                    Question question = iterator.next();
                    if (question.getRightAnswer().equals(responses.get(i).getSubmitAnswer())) {
                        score++;
                    }
                    i++;
                }

            }
        } catch (Exception e) {
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
