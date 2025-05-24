package meujogo.src.game.core;

import meujogo.src.game.entities.Boss;
import meujogo.src.game.entities.Question;

public class Zone {
    private String name;
    private int startHouse;
    private int endHouse;
    private Question[] questions; // Perguntas gerais da zona/casa
    private Boss boss;
    private boolean bossDefeated; // Novo atributo para controlar o boss

    public Zone(String name, int startHouse, int endHouse, Question[] questions, Boss boss) {
        this.name = name;
        this.startHouse = startHouse;
        this.endHouse = endHouse;
        this.questions = questions;
        this.boss = boss;
        this.bossDefeated = false; // Boss não derrotado por padrão
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

    public boolean isBossDefeated() {
        return bossDefeated;
    }

    public void setBossDefeated(boolean bossDefeated) {
        this.bossDefeated = bossDefeated;
    }
}