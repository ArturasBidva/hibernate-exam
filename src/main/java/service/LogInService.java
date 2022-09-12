package service;

import Models.user.User;
import Models.login.LoginRequest;
import Models.login.StudentLoginRequest;
import Models.login.TeacherLoginRequest;
import Models.login.LoginResponse;

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