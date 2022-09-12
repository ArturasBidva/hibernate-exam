package Models;

public class StudentSelection {
    String studentInput;
    String correctAnswer;

    public StudentSelection(String studentInput, String correctAnswer) {
        this.studentInput = studentInput;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect() {
        return studentInput.equals(correctAnswer);
    }
}
