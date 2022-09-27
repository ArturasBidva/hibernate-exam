package lt.arturas.exam.application.Models.login;

public class StudentLoginRequest implements LoginRequest{

    private final String name;

    public StudentLoginRequest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}