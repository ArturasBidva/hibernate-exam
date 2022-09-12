package lt.arturas.exam.application.repository;

import lt.arturas.exam.application.entity.ExamEntity;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ExamRepository extends AbstractRepository {

    public List<ExamEntity> getExams() {
        return getResult(session -> {
            TypedQuery<ExamEntity> query = session.createQuery(
                    "SELECT entity FROM ExamEntity entity " +
                            "LEFT JOIN FETCH entity.questionEntities " +
                            "LEFT JOIN FETCH entity.teacherEntity",
                    ExamEntity.class
            );
            return query.getResultList();
        });
    }

    public void createExam(ExamEntity examEntity) {
        modifyEntity(session -> session.persist(examEntity));
    }

    public ExamEntity getExamById(Long id) {
        return getResult(session -> {
            TypedQuery<ExamEntity> query = session.createQuery(
                    "SELECT entity FROM ExamEntity entity " +
                            "LEFT JOIN FETCH entity.questionEntities " +
                            "LEFT JOIN FETCH entity.teacherEntity " +
                            "where entity.id = ?1",
                    ExamEntity.class
            );
            query.setParameter(1, id);
            try {
                query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
            return query.getSingleResult();
        });
    }

    public void updateExam(ExamEntity examEntity) {
        modifyEntity(session -> session.update(examEntity));
    }

    public void deleteExam(ExamEntity examEntity) {
        modifyEntity(session -> session.remove(examEntity));
    }
}