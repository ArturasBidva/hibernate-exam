package lt.arturas.exam.application.repository;

import lt.arturas.exam.application.entity.QuestionEntity;

import java.util.List;

public class QuestionRepository extends AbstractRepository {
    public List<QuestionEntity> getQuestions() {
        return getResult(session -> session.createQuery(
                "SELECT entity FROM QuestionEntity entity",
                QuestionEntity.class
        ).list());
    }

    public void createQuestion(QuestionEntity questionEntity) {
        modifyEntity(session -> session.persist(questionEntity));
    }

    public QuestionEntity getQuestionById(Long id) {
        return getResult(session -> session.get(QuestionEntity.class, id));
    }
}