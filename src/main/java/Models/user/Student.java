package Models.user;

import entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements User {
    private Long id;
    private String username;
    private String name;
    private String surname;

    public Student(StudentEntity studentEntity) {
        this.id = studentEntity.getId();
        this.username = studentEntity.getUsername();
        this.name = studentEntity.getName();
        this.surname = studentEntity.getSurname();
    }

    @Override
    public Long getSessionId() {
        return id;
    }
}
