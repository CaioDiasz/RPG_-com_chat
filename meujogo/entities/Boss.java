package meujogo.entities;

public class Boss {
    private String name;
    private Question[] questions;

    public Boss(String name, Question[] questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public Question[] getQuestions() {
        return questions;
    }
}