package service;

import Models.Question;
import entity.QuestionEntity;
import repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService() {
        repository = new QuestionRepository();
    }

    public List<Question> getAllQuestions() {
        return repository.getQuestions().stream().map(Question::new).collect(Collectors.toList());
    }

    public void createQuestion(Question question) {
        repository.createQuestion(question.toQuestionEntity());
    }

}