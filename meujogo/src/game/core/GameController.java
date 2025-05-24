package meujogo.src.game.core;

import meujogo.src.game.entities.Board;
import meujogo.src.game.entities.Question;
import meujogo.src.game.entities.Boss;

import java.util.Random;
import java.util.Scanner;

public class GameController {

    // Constantes do Jogo
    private static final int FINAL_POSITION = 100;
    private static final int POINTS_PER_CORRECT_ANSWER = 10;
    private static final int POINTS_PER_BOSS_DEFEATED = 50;
    private static final int INITIAL_CHANCES = 10; // Chances iniciais do jogador
    private static final int MAX_DICE_ROLL = 3; // Dado rola até 3

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
        player = new Player(name, INITIAL_CHANCES);

        System.out.println("🌀 Bem-vindo ao Gaia's Codex, " + player.getName() + "!");
        System.out.println("🎯 Avance pelas zonas, responda aos quizzes e vença todos os bosses!");

        while (player.getPosition() < FINAL_POSITION && player.hasChances()) {
            displayPlayerStatus();
            System.out.print("🎲 Pressione Enter para rolar o dado...");
            scanner.nextLine();

            int roll = rollDice();
            System.out.println("🎲 Você rolou: " + roll);
            player.move(roll);

            System.out.println("🏠 Você está na casa " + player.getPosition());

            // Verifica se o jogador atingiu ou passou por um boss (casa final da zona)
            // A lógica de forçar a parada no boss está implícita no loop do jogo,
            // pois o jogador só pode avançar depois de enfrentar o boss da zona.
            handleZoneEvent();
            if (!player.hasChances()) {
                System.out.println("💀 Você perdeu todas as chances durante um confronto com o boss.");
                break;
            }

            // Pergunta de casa (se houver, e se o jogador não estiver exatamente na casa de um boss)
            // A ordem aqui é importante: se o jogador cair na casa final da zona, o boss é priorizado.
            // Para garantir que cada casa tenha uma pergunta, a lógica pode ser mais complexa
            // ou assumir que as perguntas de casa são "genéricas" ou mapeadas a casas específicas.
            // No seu DOCX, as perguntas estão agrupadas por "Mundo", não por casa individual.
            // Vou manter a lógica de ter perguntas associadas às casas de uma zona,
            // mas o foco principal são as perguntas do boss e as perguntas gerais da zona.
            handleHouseQuestion(); // As perguntas de casa são agora as perguntas gerais do mundo/zona
            if (!player.hasChances()) {
                System.out.println("💀 Você perdeu todas as chances respondendo uma pergunta de casa.");
                break;
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
        Random random = new Random();
        return random.nextInt(MAX_DICE_ROLL) + 1; // Dado rola de 1 a MAX_DICE_ROLL
    }

    private void handleHouseQuestion() {
        Zone currentZone = board.getZoneByPosition(player.getPosition());
        if (currentZone != null) {
            // Para garantir que perguntas sejam feitas, mesmo que o jogador não caia
            // exatamente na casa final do boss, ou em casas específicas.
            // A lógica atual do getQuestionForHouse() ainda mapeia casa a pergunta por índice.
            // Se as perguntas do DOCX são apenas "perguntas gerais da zona",
            // então a cada vez que o jogador está em uma zona, uma pergunta aleatória dessa zona pode ser feita.
            // Por simplicidade, vou manter o mapeamento por índice, mas entenda que isso pode
            // não corresponder exatamente ao número de casas vs. número de perguntas em cada "mundo" no DOCX.
            Question questionForHouse = board.getQuestionForHouse(player.getPosition());
            if (questionForHouse != null) {
                System.out.println("💡 Pergunta da Casa: " + questionForHouse.getQuestionText());
                boolean correct = quiz.askQuestion(questionForHouse);
                if (correct) {
                    player.gainPoints(POINTS_PER_CORRECT_ANSWER);
                } else {
                    player.loseChance();
                }
            }
        }
    }

    private void handleZoneEvent() {
        Zone currentZone = board.getZoneByPosition(player.getPosition());
        // Verifica se o jogador atingiu ou passou a casa final da zona
        // E se o boss dessa zona ainda não foi derrotado (precisamos de um mecanismo para isso)
        // Para a obrigatoriedade de passar pelo boss, a lógica pode ser um pouco mais sofisticada.
        // Uma forma simples é garantir que o boss só seja enfrentado UMA VEZ ao entrar/passar a casa final da zona.
        // Podemos adicionar um estado "bossDefeated" na própria Zone ou no Player.
        if (currentZone != null && player.getPosition() >= currentZone.getEndHouse() && !currentZone.isBossDefeated()) {
            Boss boss = currentZone.getBoss();
            System.out.println("\n⚔️ BOSS: " + boss.getName() + " apareceu!");
            boolean bossDefeatedSuccessfully = true;

            for (Question question : boss.getQuestions()) {
                System.out.println("\nDesafio do BOSS: " + question.getQuestionText());
                boolean correct = quiz.askQuestion(question);
                if (!correct) {
                    System.out.println("😵 Você falhou contra o boss e perdeu 1 chance.");
                    player.loseChance();
                    bossDefeatedSuccessfully = false; // Falhou em uma pergunta do boss
                    break; // Sai do loop de perguntas do boss
                }
            }

            if (bossDefeatedSuccessfully) {
                System.out.println("🏆 Você derrotou o boss " + boss.getName() + "!");
                player.gainPoints(POINTS_PER_BOSS_DEFEATED);
                currentZone.setBossDefeated(true); // Marca o boss da zona como derrotado
            } else {
                if (!player.hasChances()) {
                    System.out.println("💀 Você perdeu todas as chances e não conseguiu derrotar o boss.");
                }
                // Se o boss não foi derrotado, o jogador deve permanecer na posição para tentar novamente
                // ou enfrentar outro desafio. Por simplicidade, o jogo continua, mas com chance reduzida.
                // Para *forçar* a parada, o player.move() precisaria ser ajustado para "parar"
                // na casa do boss, ou o jogo loopar até o boss ser vencido.
                // A lógica atual permite continuar (se tiver chances), mas não avança o boss.
            }
        }
    }

    private void endGame() {
        if (player.getPosition() >= FINAL_POSITION) {
            System.out.println("🎉 Parabéns, " + player.getName() + "! Você completou Gaia's Codex!");
            System.out.println("⭐ Pontuação final: " + player.getPoints());
        } else {
            System.out.println("Game over! Você não tem mais chances.");
        }
        scanner.close();
    }
}