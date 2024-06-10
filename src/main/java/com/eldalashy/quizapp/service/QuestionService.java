package com.eldalashy.quizapp.service;

import com.eldalashy.quizapp.dao.QuestionDao;
import com.eldalashy.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity < List < Question >> getAllQuestions() {
        return new ResponseEntity < > (questionDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity < List < Question >> getQuestionByCategory(String category) {
        return new ResponseEntity < > (questionDao.findByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity < String > addQuestion(Question question) {
        try {
            questionDao.save(question);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity < > (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity < > ("New question added successfully ", HttpStatus.CREATED);
    }

    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "Question no " + id + " Deleted ";
    }

    public ResponseEntity < String > updateQuestion(Question question) {
        {
            try {
                questionDao.save(question);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity < > (e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity < > ("Question Updated successfully ", HttpStatus.CREATED);
        }
    }

}