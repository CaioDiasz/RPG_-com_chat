// Pacote onde esta classe está localizada
package meujogo.entities;

/**
 * Classe que representa um Boss (chefe) no jogo.
 * Cada Boss possui um nome e um conjunto de perguntas
 * que devem ser respondidas para derrotá-lo.
 */
public class Boss {

    // Atributo que armazena o nome do boss (chefe).
    private String name;

    // Vetor de perguntas que estão associadas ao boss.
    private Question[] questions;

    /**
     * Construtor da classe Boss.
     * Responsável por criar uma instância de Boss com nome e perguntas associadas.
     *
     * @param name      Nome do boss.
     * @param questions Vetor de perguntas que fazem parte do desafio do boss.
     */
    public Boss(String name, Question[] questions) {
        this.name = name;
        this.questions = questions;
    }

    /**
     * Método getter para obter o nome do boss.
     *
     * @return nome do boss.
     */
    public String getName() {
        return name;
    }

    /**
     * Método getter para obter as perguntas associadas ao boss.
     *
     * @return vetor de perguntas do boss.
     */
    public Question[] getQuestions() {
        return questions;
    }
}
