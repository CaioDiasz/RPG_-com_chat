package meujogo.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import meujogo.entities.Boss;
import meujogo.entities.Question;

public class Zone {
    private String name;
    private int startHouse;
    private int endHouse;
    private List<Question> availableQuestions; // Usar List para remover perguntas
    private Boss boss;
    private boolean bossDefeated;
    private Random random;

    public Zone(String name, int startHouse, int endHouse, Question[] questions, Boss boss) {
        this.name = name;
        this.startHouse = startHouse;
        this.endHouse = endHouse;
        this.availableQuestions = new ArrayList<>(Arrays.asList(questions)); // Copia para uma lista mutável
        this.boss = boss;
        this.bossDefeated = false;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public boolean isInZone(int position) {
        return position >= startHouse && position <= endHouse;
    }

    public Question[] getQuestions() {
        return availableQuestions.toArray(new Question[0]); // Retorna como array para compatibilidade, mas o controle é da lista
    }

    // Novo método para obter a próxima pergunta disponível aleatoriamente e removê-la
    public Question getNextAvailableQuestion() {
        if (!availableQuestions.isEmpty()) {
            int index = random.nextInt(availableQuestions.size());
            return availableQuestions.remove(index); // Remove e retorna a pergunta
        }
        return null; // Nenhuma pergunta disponível
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