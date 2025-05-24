package meujogo.src.game.core;

import meujogo.src.game.entities.Question;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Quiz {

    private static final int MAX_ATTEMPTS = 3; // Número máximo de tentativas por pergunta

    private Scanner scanner;

    public Quiz() {
        this.scanner = new Scanner(System.in);
    }

    public boolean askQuestion(Question question) {
        int attempts = MAX_ATTEMPTS;
        while (attempts > 0) {
            System.out.println("\n📜 Pergunta:");
            System.out.println(question.getQuestionText());

            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            System.out.print("Escolha sua resposta (1-" + options.length + "): ");
            int choice = -1;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Por favor, digite um NÚMERO.");
                attempts--;
                continue;
            }

            if (choice < 1 || choice > options.length) {
                System.out.println("⚠️ Opção inválida. Digite um número entre 1 e " + options.length + ".");
                attempts--;
                continue;
            }

            String selectedAnswer = options[choice - 1];

            if (question.checkAnswer(selectedAnswer)) {
                System.out.println("✅ Resposta correta!");
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("❌ Resposta errada. Tentativas restantes: " + attempts);
                } else {
                    System.out.println("😢 Você errou todas as tentativas.");
                }
            }
        }
        return false;
    }
}