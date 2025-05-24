package meujogo.src.game.core;

import meujogo.src.game.entities.Board;
import meujogo.src.game.entities.Question;
import meujogo.src.game.entities.Boss;

import java.util.Random;
import java.util.Scanner;

public class GameController {

    private static final int FINAL_POSITION = 100;
    private static final int POINTS_PER_CORRECT_ANSWER = 10;
    private static final int POINTS_PER_BOSS_DEFEATED = 50;
    private static final int INITIAL_CHANCES = 10;
    private static final int MAX_DICE_ROLL = 3; // Dado rola até 3

    private Player player;
    private Quiz quiz;
    private Board board;
    private Scanner scanner;
    private Random random;
    private Zone currentZone; // Para rastrear a zona atual do jogador e detectar mudança

    public GameController() {
        this.quiz = new Quiz();
        this.board = new Board();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void startGame() {
        System.out.print("Digite seu nome, herói: ");
        String name = scanner.nextLine();
        player = new Player(name, INITIAL_CHANCES);

        System.out.println("🌀 Bem-vindo ao Gaia's Codex, " + player.getName() + "!");
        System.out.println("🎯 Avance pelas zonas, responda aos quizzes e vença todos os bosses!");

        // Inicializa a zona atual
        currentZone = board.getZoneByPosition(player.getPosition());
        if (currentZone != null) {
            System.out.println("\n🌎 Você entrou na área: " + currentZone.getName() + "!");
        }

        while (player.getPosition() < FINAL_POSITION && player.hasChances()) {
            displayPlayerStatus();
            System.out.print("🎲 Pressione Enter para rolar o dado...");
            scanner.nextLine();

            int roll = rollDice();
            System.out.println("🎲 Você rolou: " + roll);

            int newPotentialPosition = player.getPosition() + roll;
            
            // Verifica a zona de destino antes de mover o jogador
            Zone nextZone = board.getZoneByPosition(newPotentialPosition);
            
            // Lógica para forçar parada no boss (se a zona não foi derrotada)
            boolean movedIntoOrPastBossZone = false;
            if (nextZone != null && !nextZone.isBossDefeated() && newPotentialPosition >= nextZone.getEndHouse()) {
                player.setPosition(nextZone.getEndHouse()); // Teleporta para a casa do boss
                movedIntoOrPastBossZone = true;
                System.out.println("🚨 Você atingiu a área do boss da " + nextZone.getName() + "!");
            } else {
                player.move(roll); // Movimento normal
            }

            System.out.println("🏠 Você está na casa " + player.getPosition());

            // Verifica se o jogador entrou em uma nova zona
            Zone newCurrentZone = board.getZoneByPosition(player.getPosition());
            if (newCurrentZone != null && newCurrentZone != currentZone) {
                currentZone = newCurrentZone;
                System.out.println("\n🌎 Você entrou na área: " + currentZone.getName() + "!");
            }

            // Prioriza o evento do boss se o jogador parou na casa do boss e ele ainda não foi derrotado
            if (movedIntoOrPastBossZone || (currentZone != null && player.getPosition() == currentZone.getEndHouse() && !currentZone.isBossDefeated())) {
                handleZoneEvent();
                if (!player.hasChances()) {
                    System.out.println("💀 Você perdeu todas as chances durante um confronto com o boss.");
                    break; // Game Over
                }
            } else {
                // Se não é a casa do boss, tenta fazer uma pergunta de casa
                handleHouseQuestion();
                if (!player.hasChances()) {
                    System.out.println("💀 Você perdeu todas as chances respondendo uma pergunta de casa.");
                    break; // Game Over
                }
            }
        }

        endGame();
    }

    private void displayPlayerStatus() {
        System.out.println("\n🏁 Posição atual: " + player.getPosition() +
                           " | ❤️ Chances: " + player.getChances() +
                           " | ✨ Pontos: " + player.getPoints());
    }

    private int rollDice() {
        return random.nextInt(MAX_DICE_ROLL) + 1; // Dado rola de 1 a MAX_DICE_ROLL
    }

    private void handleHouseQuestion() {
        Zone currentZone = board.getZoneByPosition(player.getPosition());
        if (currentZone != null) {
            Question questionForHouse = currentZone.getNextAvailableQuestion(); // Pega a próxima pergunta disponível
            if (questionForHouse != null) {
                System.out.println("💡 Pergunta da Casa (" + currentZone.getName() + "): " + questionForHouse.getQuestionText());
                boolean correct = quiz.askQuestion(questionForHouse);
                if (correct) {
                    player.gainPoints(POINTS_PER_CORRECT_ANSWER);
                } else {
                    player.loseChance();
                }
            } else {
                System.out.println("🍃 Esta casa está tranquila. Não há mais perguntas disponíveis nesta área.");
            }
        }
    }

    private void handleZoneEvent() {
        Zone currentZone = board.getZoneByPosition(player.getPosition());
        if (currentZone != null && player.getPosition() == currentZone.getEndHouse() && !currentZone.isBossDefeated()) {
            Boss boss = currentZone.getBoss();
            System.out.println("\n⚔️ BOSS: " + boss.getName() + " apareceu na " + currentZone.getName() + "!");
            boolean bossDefeatedSuccessfully = true;

            if (boss.getQuestions().length > 0) {
                for (Question question : boss.getQuestions()) {
                    System.out.println("\nDesafio do BOSS: " + question.getQuestionText());
                    boolean correct = quiz.askQuestion(question);
                    if (!correct) {
                        System.out.println("😵 Você falhou contra o boss e perdeu 1 chance.");
                        player.loseChance();
                        bossDefeatedSuccessfully = false;
                        break;
                    }
                }
            } else {
                System.out.println("O boss não tem perguntas, ele é derrotado automaticamente!");
            }

            if (bossDefeatedSuccessfully) {
                System.out.println("🏆 Você derrotou o boss " + boss.getName() + "!");
                player.gainPoints(POINTS_PER_BOSS_DEFEATED);
                currentZone.setBossDefeated(true); // Marca o boss da zona como derrotado
            } else {
                System.out.println("Você não derrotou o boss e permanece na casa " + player.getPosition() + ".");
                if (!player.hasChances()) {
                    System.out.println("💀 Você perdeu todas as chances e não conseguiu derrotar o boss.");
                }
            }
        }
    }

    private void endGame() {
        if (player.getPosition() >= FINAL_POSITION) {
            System.out.println("\n🎉 Parabéns, " + player.getName() + "! Você completou Gaia's Codex!");
            System.out.println("⭐ Pontuação final: " + player.getPoints());
        } else {
            System.out.println("\nGame over! Você não tem mais chances.");
            System.out.println("⭐ Pontuação final: " + player.getPoints());
        }
        scanner.close();
    }
}