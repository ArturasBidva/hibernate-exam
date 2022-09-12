package lt.arturas.exam.application.repository;

import lt.arturas.exam.application.entity.StudentResultEntity;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentResultRepository extends AbstractRepository {

    public void createStudentResult(StudentResultEntity studentResultEntity) {
        modifyEntity(session -> session.persist(studentResultEntity));
    }

    public List<StudentResultEntity> getStudentResults() {
        return getResult(session -> {
            TypedQuery<StudentResultEntity> query = session.createQuery(
                    "SELECT entity FROM StudentResultEntity entity " +
                            "LEFT JOIN FETCH entity.studentEntity " +
                            "LEFT JOIN FETCH entity.examEntity " +
                            "LEFT JOIN FETCH entity.examEntity.questionEntities " +
                            "LEFT JOIN FETCH entity.examEntity.teacherEntity",
                    StudentResultEntity.class
            );
            return query.getResultList();
        });
    }

    public List<StudentResultEntity> getStudentResultsByStudentId(Long id) {
        return getResult(session -> {
            TypedQuery<StudentResultEntity> query = session.createQuery(
                    "SELECT entity FROM StudentResultEntity entity " +
                            "LEFT JOIN FETCH entity.studentEntity " +
                            "LEFT JOIN FETCH entity.examEntity " +
                            "LEFT JOIN FETCH entity.examEntity.teacherEntity " +
                            "where entity.studentEntity.id = ?1",
                    StudentResultEntity.class
            );
            query.setParameter(1, id);
            return query.getResultList();
        });
    }

    public StudentResultEntity getStudentResultById(Long id) {
        return getResult(session -> session.get(StudentResultEntity.class, id));
    }

    public void updateStudentResult(StudentResultEntity studentResultEntity) {
        modifyEntity(session -> session.update(studentResultEntity));
    }
}