package lt.arturas.exam.application.service;

import lt.arturas.exam.application.Models.user.User;
import lt.arturas.exam.application.Models.login.LoginRequest;
import lt.arturas.exam.application.Models.login.StudentLoginRequest;
import lt.arturas.exam.application.Models.login.TeacherLoginRequest;
import lt.arturas.exam.application.Models.login.LoginResponse;

public class LogInService {
    private final TeacherService teacherService;
    private final StudentService studentService;
    public static Long currentSessionId;

    public LogInService() {
        teacherService = new TeacherService();
        studentService = new StudentService();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = null;
        if (loginRequest instanceof TeacherLoginRequest) {
            user = teacherService.getTeacherByName(loginRequest.getName());
        } else if (loginRequest instanceof StudentLoginRequest) {
            user = studentService.getStudentByName(loginRequest.getName());
        }
        return validateLoginRequest(user);
    }

    private LoginResponse validateLoginRequest(User user) {
        if (user == null) {

            return LoginResponse.FAILED;
        } else {
            completeLogIn(user);
            return LoginResponse.SUCCESS;
        }
    }

    public void completeLogIn(User user) {
        currentSessionId = user.getSessionId();
    }

    public Long getCurrentSessionId() {
        return currentSessionId;
    }
}