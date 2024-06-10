package com.eldalashy.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Response {

    private Integer id;
    private String submitAnswer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(id, response.id) && Objects.equals(submitAnswer, response.submitAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, submitAnswer);
    }
}
