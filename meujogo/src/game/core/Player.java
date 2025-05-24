package meujogo.src.game.core;

public class Player {
    private String name;
    private int position;
    private int points;
    private int chances;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.points = 0;
        this.chances = 10; // total de chances (vidas)
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move(int steps) {
        position += steps;
        if (position > 100) {
            position = 100;
        }
    }

    public int getPoints() {
        return points;
    }

    public void gainPoints(int value) {
        points += value;
    }

    public void loseChance() {
        chances--;
    }

    public int getChances() {
        return chances;
    }

    public boolean hasChances() {
        return chances > 0;
    }
}
