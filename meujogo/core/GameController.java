// Chamamos o pacote principal do jogo
package meujogo.core;

import java.util.Random;
import java.util.Scanner;

import meujogo.entities.Board;
import meujogo.entities.Boss;
import meujogo.entities.Question;

//Criamos a Classe principal que controla o fluxo do jogo.
public class GameController {

    // Constantes de pontos do jogo
    private static final int FINAL_POSITION = 100; // Posição final no tabuleiro
    private static final int POINTS_PER_CORRECT_ANSWER = 10; // Pontos por resposta correta
    private static final int POINTS_PER_BOSS_DEFEATED = 50;  // Pontos por derrotar um boss
    private static final int INITIAL_CHANCES = 10;           // Número inicial de chances
    private static final int MAX_DICE_ROLL = 3;              // Valor máximo do dado (1 a 3)

    // Objetos do jogo
    private Player player;       // Jogador
    private Quiz quiz;           // Sistema de perguntas
    private Board board;         // Tabuleiro do jogo
    private Scanner scanner;     // Leitor de entrada
    private Random random;       // Gerador de números aleatórios
    private Zone currentZone;    // Zona atual do jogador (para detectar mudanças)

    // Modo Construtor do GameController
    public GameController() {
        this.quiz = new Quiz();
        this.board = new Board();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    // Inicia o jogo.
    public void startGame() {
        // Solicitamos o nome do jogador
        System.out.print("Digite seu nome, herói: ");
        String name = scanner.nextLine();
        player = new Player(name, INITIAL_CHANCES);

        // Print mensagem de boas-vindas
        System.out.println(" Bem-vindo ao Gaia's Codex, " + player.getName() + "!");
        System.out.println(" Avance pelas zonas, responda aos quizzes e vença todos os bosses!");

        // Inicializa a zona atual com base na posição inicial do jogador
        currentZone = board.getZoneByPosition(player.getPosition());
        if (currentZone != null) {
            System.out.println("\n Você entrou na área: " + currentZone.getName() + "!");
        }

        // Loop principal do jogo (enquanto não chega na posição final e tem chances)
        while (player.getPosition() < FINAL_POSITION && player.hasChances()) {
            displayPlayerStatus();

            // Jogador rola o dado (aleatório)
            System.out.print(" Pressione Enter para rolar o dado...");
            scanner.nextLine();
            int roll = rollDice();
            System.out.println(" Você rolou: " + roll);

            int newPotentialPosition = player.getPosition() + roll;

            // Verifica se vai entrar ou passar por uma zona de boss "chefão" não derrotado
            Zone nextZone = board.getZoneByPosition(newPotentialPosition);
            boolean movedIntoOrPastBossZone = false;

            if (nextZone != null && !nextZone.isBossDefeated() &&
                newPotentialPosition >= nextZone.getEndHouse()) {
                // Força o jogador a parar na casa do boss "chefão"
                player.setPosition(nextZone.getEndHouse());
                movedIntoOrPastBossZone = true;
                System.out.println(" Você atingiu a área do boss da " + nextZone.getName() + "!");
            } else {
                // Movimento normal
                player.move(roll);
            }

            System.out.println(" Você está na casa " + player.getPosition());

            // Verifica se avançou de zona
            Zone newCurrentZone = board.getZoneByPosition(player.getPosition());
            if (newCurrentZone != null && newCurrentZone != currentZone) {
                currentZone = newCurrentZone;
                System.out.println("\n Você entrou na área: " + currentZone.getName() + "!");
            }

            // Checa se o jogador está na casa do boss "chefão" da zona atual e se ele não foi derrotado
            if (movedIntoOrPastBossZone ||
                (currentZone != null &&
                player.getPosition() == currentZone.getEndHouse() &&
                !currentZone.isBossDefeated())) {

                handleZoneEvent(); // Chama o Objeto boss "chefão"

                // Se perder todas as vidas no boss
                if (!player.hasChances()) {
                    System.out.println(" Você perdeu todas as chances durante um confronto com o boss.");
                    break; // Fim de jogo
                }

            } else {
                // Caso contrário, faz pergunta normal de casa
                handleHouseQuestion();

                // Se perder todas as vidas na pergunta
                if (!player.hasChances()) {
                    System.out.println(" Você perdeu todas as chances respondendo uma pergunta de casa.");
                    break;
                }
            }
        }

        endGame(); // Encerrar jogo
    }

    // Exibe o status atual do jogador.
    private void displayPlayerStatus() {
        System.out.println("\n🏁 Posição atual: " + player.getPosition() +
                           " |  Chances: " + player.getChances() +
                           " |  Pontos: " + player.getPoints());
    }

    // Simula o lançamento do dado (1 a MAX_DICE_ROLL).
    private int rollDice() {
        return random.nextInt(MAX_DICE_ROLL) + 1;
    }

    // Gerencia as perguntas de uma casa comum (não boss) para uma de boss "chefão".
    private void handleHouseQuestion() {
        Zone currentZone = board.getZoneByPosition(player.getPosition());

        if (currentZone != null) {
            Question questionForHouse = currentZone.getNextAvailableQuestion();

            if (questionForHouse != null) {
                System.out.println(" Pergunta da Casa (" + currentZone.getName() + "): " +
                                   questionForHouse.getQuestionText());

                boolean correct = quiz.askQuestion(questionForHouse);

                if (correct) {
                    player.gainPoints(POINTS_PER_CORRECT_ANSWER);
                } else {
                    player.loseChance();
                }

            } else {
                System.out.println(" Esta casa está tranquila. Não há mais perguntas disponíveis nesta área.");
            }
        }
    }

    // Gerencia o evento da zona — combate com o boss "chefão".
    private void handleZoneEvent() {
        Zone currentZone = board.getZoneByPosition(player.getPosition());

        // Verifica se está na casa do boss "chefão" e se ele não foi derrotado.
        if (currentZone != null &&
            player.getPosition() == currentZone.getEndHouse() &&
            !currentZone.isBossDefeated()) {

            Boss boss = currentZone.getBoss();
            System.out.println("\n BOSS: " + boss.getName() + " apareceu na " +
                               currentZone.getName() + "!");

            boolean bossDefeatedSuccessfully = true;

            // Executa todas as perguntas do boss "chefão".
            if (boss.getQuestions().length > 0) {
                for (Question question : boss.getQuestions()) {
                    System.out.println("\nDesafio do BOSS: " + question.getQuestionText());
                    boolean correct = quiz.askQuestion(question);

                    if (!correct) {
                        System.out.println(" Você falhou contra o boss e perdeu 1 chance.");
                        player.loseChance();
                        bossDefeatedSuccessfully = false;
                        break;
                    }
                }
            } else {
                System.out.println("O boss não tem perguntas, ele é derrotado automaticamente!");
            }

            if (bossDefeatedSuccessfully) {
                System.out.println(" Você derrotou o boss " + boss.getName() + "!");
                player.gainPoints(POINTS_PER_BOSS_DEFEATED);
                currentZone.setBossDefeated(true);
            } else {
                System.out.println("Você não derrotou o boss e permanece na casa " + player.getPosition() + ".");
                if (!player.hasChances()) {
                    System.out.println(" Você perdeu todas as chances e não conseguiu derrotar o boss.");
                }
            }
        }
    }

    // Finaliza o jogo exibindo mensagens de encerramento.
    private void endGame() {
        if (player.getPosition() >= FINAL_POSITION) {
            System.out.println("\n Parabéns, " + player.getName() + "! Você completou Gaia's Codex!");
            System.out.println("Pontuação final: " + player.getPoints());
        } else {
            System.out.println("\nGame over! Você não tem mais chances.");
            System.out.println(" Pontuação final: " + player.getPoints());
        }
        scanner.close();
    }
}
