package meujogo.src.game.core;

import meujogo.src.game.entities.Board;
import meujogo.src.game.entities.Question;
import meujogo.src.game.entities.Boss;

import java.util.Random;
import java.util.Scanner;

public class GameController {

    private Player player;
    private Quiz quiz;
    private Board board;
    private Scanner scanner;

    public GameController() {
        this.quiz = new Quiz();
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.print("Digite seu nome, herói: ");
        String name = scanner.nextLine();
        player = new Player(name);

        System.out.println("🌀 Bem-vindo ao Gaia's Codex, " + player.getName() + "!");
        System.out.println("🎯 Avance pelas zonas, responda aos quizzes e vença todos os bosses!");

        while (player.getPosition() < 100 && player.hasChances()) {
            System.out.println("\n🏁 Posição atual: " + player.getPosition() + " | ❤️ Chances: " + player.getChances() + " | ✨ Pontos: " + player.getPoints());
            System.out.print("🎲 Pressione Enter para rolar o dado...");
            scanner.nextLine();

            int roll = rollDice();
            System.out.println("🎲 Você rolou: " + roll);
            player.move(roll);

            // Exibe a posição do jogador
            System.out.println("🏠 Você está na casa " + player.getPosition());

            Question questionForHouse = board.getQuestionForHouse(player.getPosition());
            if (questionForHouse != null) {
                System.out.println("💡 Pergunta: " + questionForHouse.getQuestionText());
                boolean correct = quiz.askQuestion(questionForHouse);
                if (correct) {
                    player.gainPoints(10);
                } else {
                    player.loseChance();
                    if (!player.hasChances()) {
                        System.out.println("💀 Você perdeu todas as chances.");
                        break;
                    }
                }
            }

            // Bosses e perguntas de zona
            Zone currentZone = board.getZoneByPosition(player.getPosition());
            if (currentZone != null && player.getPosition() >= currentZone.getEndHouse()) {
                Boss boss = currentZone.getBoss();
                System.out.println("\n⚔️ BOSS: " + boss.getName() + " apareceu!");
                for (Question question : boss.getQuestions()) {
                    boolean correct = quiz.askQuestion(question);
                    if (correct) {
                        System.out.println("🏆 Você derrotou o boss " + boss.getName() + "!");
                        player.gainPoints(50);
                    } else {
                        System.out.println("😵 Você falhou contra o boss e perdeu 1 chance.");
                        player.loseChance();
                        if (!player.hasChances()) {
                            System.out.println("💀 Você perdeu todas as chances.");
                            return;
                        }
                    }
                }
            }
        }

        if (player.getPosition() >= 100) {
            System.out.println("🎉 Parabéns, " + player.getName() + "! Você completou Gaia's Codex!");
            System.out.println("⭐ Pontuação final: " + player.getPoints());
        } else {
            System.out.println("Game over! Você não tem mais chances.");
        }
    }

    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
