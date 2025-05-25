// Pacote onde está localizada a classe Quiz
package meujogo.core;

import java.util.Scanner;
import meujogo.entities.Question;

/**
 * Classe responsável por gerenciar os quizzes do jogo.
 * Ela apresenta perguntas e verifica se as respostas estão corretas.
 */
public class Quiz {

    // Número máximo de tentativas permitidas por pergunta
    private static final int MAX_ATTEMPTS = 3;

    // Scanner para ler entradas do usuário
    private Scanner scanner;

    /**
     * Construtor da classe Quiz.
     * Inicializa o scanner para capturar entradas do teclado.
     */
    public Quiz() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Método que apresenta uma pergunta ao jogador e gerencia suas tentativas.
     * O jogador tem até MAX_ATTEMPTS para acertar.
     * 
     * @param question Objeto da classe Question contendo texto, opções e resposta correta.
     * @return true se o jogador acertou, false se errou todas as tentativas.
     */
    public boolean askQuestion(Question question) {
        int attempts = MAX_ATTEMPTS; // Inicializa as tentativas para a pergunta

        while (attempts > 0) {
            // Exibe o enunciado da pergunta
            System.out.println("\n Pergunta:");
            System.out.println(question.getQuestionText());

            // Exibe as opções de resposta
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]); // Numera as opções (1, 2, 3, ...)
            }

            // Solicita que o jogador insira sua escolha
            System.out.print("Escolha sua resposta (1-" + options.length + "): ");
            int choice = -1;

            try {
                // Lê e tenta converter a entrada do jogador para inteiro
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Caso o usuário não insira um número válido
                System.out.println(" Entrada inválida. Por favor, digite um NÚMERO.");
                attempts--; // Perde uma tentativa
                continue;   // Volta para o início do loop
            }

            // Verifica se a opção está dentro dos limites válidos
            if (choice < 1 || choice > options.length) {
                System.out.println(" Opção inválida. Digite um número entre 1 e " + options.length + ".");
                attempts--; // Perde uma tentativa
                continue;   // Volta para o início do loop
            }

            // Obtém a resposta selecionada pelo jogador
            String selectedAnswer = options[choice - 1];

            // Verifica se a resposta está certa
            if (question.checkAnswer(selectedAnswer)) {
                System.out.println(" ✅ Resposta correta!");
                return true; // Resposta certa encerra o método
            } else {
                attempts--; // Perde uma tentativa
                if (attempts > 0) {
                    System.out.println(" ❌ Resposta errada. Tentativas restantes: " + attempts);
                } else {
                    System.out.println(" ❌ Você errou todas as tentativas.");
                }
            }
        }

        // Se saiu do loop, significa que errou todas as tentativas
        return false;
    }
}
