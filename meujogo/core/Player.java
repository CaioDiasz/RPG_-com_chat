package meujogo.core;

public class Player {
    private String name;
    private int position;
    private int points;
    private int chances;

    public Player(String name, int initialChances) {
        this.name = name;
        this.position = 0;
        this.points = 0;
        this.chances = initialChances; // total de chances (vidas)
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) { // Novo setter para ajustar a posição
        this.position = position;
    }

    public void move(int steps) {
        position += steps;
        // Garante que o jogador não ultrapasse a posição final se rolar um dado muito alto
        if (position > 100) {
            position = 100;
        }
    }

    public int getPoints() {
        return points;
    }

    public void gainPoints(int value) {
        if (value > 0) {
            points += value;
        }
    }

    public void loseChance() {
        if (chances > 0) {
            chances--;
        }
    }

    public int getChances() {
        return chances;
    }

    public boolean hasChances() {
        return chances > 0;
    }
}