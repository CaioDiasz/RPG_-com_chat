package meujogo.entities;

// Se esta classe não for utilizada em nenhuma outra parte do projeto, considere removê-la.
public class Enemy {
    private String name;
    private int difficulty;

    public Enemy(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }
}