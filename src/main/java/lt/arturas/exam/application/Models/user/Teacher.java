package lt.arturas.exam.application.Models.user;

import lt.arturas.exam.application.entity.TeacherEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements User {
    private Long id;
    private String name;

    public Teacher(TeacherEntity teacherEntity) {
        this.id = teacherEntity.getId();
        this.name = teacherEntity.getName();
    }

    @Override
    public Long getSessionId() {
        return id;
    }
}