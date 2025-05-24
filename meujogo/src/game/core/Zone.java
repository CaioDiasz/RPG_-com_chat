package meujogo.src.game.core;

import meujogo.src.game.entities.Boss;
import meujogo.src.game.entities.Question;

public class Zone {
    private String name;
    private int startHouse;
    private int endHouse;
    private Question[] questions;
    private Boss boss;

    public Zone(String name, int startHouse, int endHouse, Question[] questions, Boss boss) {
        this.name = name;
        this.startHouse = startHouse;
        this.endHouse = endHouse;
        this.questions = questions;
        this.boss = boss;
    }

    public String getName() {
        return name;
    }

    public boolean isInZone(int position) {
        return position >= startHouse && position <= endHouse;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public Boss getBoss() {
        return boss;
    }

    public int getStartHouse() {
        return startHouse;
    }

    public int getEndHouse() {
        return endHouse;
    }
}
