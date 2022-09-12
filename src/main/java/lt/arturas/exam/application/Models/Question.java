package lt.arturas.exam.application.Models;

import lt.arturas.exam.application.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Question {
    private Long questionID;
    private String question;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    @Getter
    private String correctAnswer;

    public Question(QuestionEntity questionEntity) {
        this.questionID = questionEntity.getId();
        this.question = questionEntity.getQuestion();
        this.answer_1 = questionEntity.getAnswer_1();
        this.answer_2 = questionEntity.getAnswer_2();
        this.answer_3 = questionEntity.getAnswer_3();
        this.correctAnswer = questionEntity.getCorrectAnswer();
    }

    public QuestionEntity toQuestionEntity() {
        return new QuestionEntity(questionID, question, answer_1, answer_2, answer_3, correctAnswer);
    }

    @Override
    public String toString() {
        return "Question{" + '\'' +
                "questionID=" + questionID +
                ", QUESTION='" + question + '\'' +
                ", ANSWER_1='" + answer_1 + '\'' +
                ", ANSWER_2='" + answer_2 + '\'' +
                ", ANSWER_3='" + answer_3 + '\'' +
                '}';
    }
}