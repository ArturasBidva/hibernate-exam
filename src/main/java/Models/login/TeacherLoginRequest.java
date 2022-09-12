package Models.login;

public class TeacherLoginRequest implements LoginRequest{

    private final String name;

    public TeacherLoginRequest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
