// Define o pacote onde esta classe está localizada
package meujogo.entities;

/**
 * Classe que representa uma pergunta do jogo.
 * Cada pergunta possui um enunciado, múltiplas opções de resposta
 * e uma resposta correta.
 */
public class Question {

    // Texto da pergunta (enunciado)
    private String questionText;

    // Array de strings que representa as opções de resposta
    private String[] options;

    // Resposta correta da pergunta
    private String correctAnswer;

    /**
     * Construtor da classe Question.
     * Inicializa a pergunta com seu texto, opções e resposta correta.
     *
     * @param questionText   Texto da pergunta (enunciado).
     * @param options        Vetor com as opções de resposta.
     * @param correctAnswer  String com a resposta correta.
     */
    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Retorna o texto da pergunta (enunciado).
     *
     * @return Texto da pergunta.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Retorna o vetor com as opções de resposta.
     *
     * @return Array de strings contendo as opções.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Retorna a resposta correta da pergunta.
     *
     * @return String com a resposta correta.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Método que verifica se a resposta passada é correta.
     * A comparação ignora maiúsculas e minúsculas.
     *
     * @param answer Resposta fornecida pelo jogador.
     * @return true se a resposta estiver correta, false caso contrário.
     */
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}
