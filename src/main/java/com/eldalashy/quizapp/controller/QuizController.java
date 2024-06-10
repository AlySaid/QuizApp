package com.eldalashy.quizapp.controller;

import com.eldalashy.quizapp.model.Question;
import com.eldalashy.quizapp.model.QuestionWrapper;
import com.eldalashy.quizapp.model.Response;
import com.eldalashy.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    //    http://localhost:8080/quiz/create?cat=Java&numQ=5&title=JavaQuiz
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String cat, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(cat, numQ, title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable("id") Integer id) {
        return quizService.getQuizById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable("id") Integer id, @RequestBody List<Response> responses) {
        Integer score = quizService.calculateScore(id, responses).getBody();
        return new ResponseEntity<String>("Success with Score " + score, HttpStatus.OK);

    }
}
