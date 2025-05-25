// Pacote onde está localizada a classe Zone
package meujogo.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import meujogo.entities.Boss;
import meujogo.entities.Question;

/**
 * A classe Zone representa uma zona ou área do tabuleiro.
 * Cada zona tem um intervalo de casas, perguntas associadas e um boss.
 */
public class Zone {

    // Atributos da zona
    private String name;                        // Nome da zona
    private int startHouse;                     // Casa inicial da zona
    private int endHouse;                       // Casa final da zona
    private List<Question> availableQuestions;  // Lista de perguntas disponíveis na zona
    private Boss boss;                          // Boss associado à zona
    private boolean bossDefeated;               // Flag para indicar se o boss já foi derrotado
    private Random random;                      // Gerador de números aleatórios para sortear perguntas

    /**
     * Construtor da classe Zone.
     * 
     * @param name Nome da zona.
     * @param startHouse Casa inicial da zona.
     * @param endHouse Casa final da zona.
     * @param questions Array de perguntas da zona.
     * @param boss Boss da zona.
     */
    public Zone(String name, int startHouse, int endHouse, Question[] questions, Boss boss) {
        this.name = name;
        this.startHouse = startHouse;
        this.endHouse = endHouse;
        // Copia o array de perguntas para uma lista mutável
        this.availableQuestions = new ArrayList<>(Arrays.asList(questions));
        this.boss = boss;
        this.bossDefeated = false; // No início, o boss não está derrotado
        this.random = new Random();
    }

    /** 
     * Retorna o nome da zona.
     */
    public String getName() {
        return name;
    }

    /**
     * Verifica se uma posição (casa) pertence a essa zona.
     * 
     * @param position A posição a ser verificada.
     * @return true se a posição está dentro da zona, false caso contrário.
     */
    public boolean isInZone(int position) {
        return position >= startHouse && position <= endHouse;
    }

    /**
     * Retorna as perguntas restantes da zona em formato de array.
     * (Útil para compatibilidade, embora internamente seja uma lista.)
     */
    public Question[] getQuestions() {
        return availableQuestions.toArray(new Question[0]);
    }

    /**
     * Retorna uma pergunta aleatória da lista de disponíveis e a remove da lista.
     * 
     * @return Uma pergunta, ou null se não houver mais disponíveis.
     */
    public Question getNextAvailableQuestion() {
        if (!availableQuestions.isEmpty()) {
            int index = random.nextInt(availableQuestions.size()); // Sorteia um índice
            return availableQuestions.remove(index);               // Remove e retorna a pergunta
        }
        return null; // Se não há mais perguntas disponíveis
    }

    /**
     * Retorna o boss da zona.
     */
    public Boss getBoss() {
        return boss;
    }

    /**
     * Retorna a casa inicial da zona.
     */
    public int getStartHouse() {
        return startHouse;
    }

    /**
     * Retorna a casa final da zona (onde fica o boss).
     */
    public int getEndHouse() {
        return endHouse;
    }

    /**
     * Verifica se o boss já foi derrotado.
     */
    public boolean isBossDefeated() {
        return bossDefeated;
    }

    /**
     * Define o estado do boss (derrotado ou não).
     * 
     * @param bossDefeated true se foi derrotado, false caso contrário.
     */
    public void setBossDefeated(boolean bossDefeated) {
        this.bossDefeated = bossDefeated;
    }
}
