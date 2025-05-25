// Pacote principal onde está localizada a classe Player
package meujogo.core;

/**
 * Classe que representa o jogador no jogo.
 * Armazena informações como nome, posição no tabuleiro, pontuação e chances restantes.
 */
public class Player {
    private String name;
    private int position;
    private int points;
    private int chances;

    /**
     * Construtor da classe Player.
     * Inicializa o jogador com nome, chances, posição inicial (0) e pontos (0).
     * 
     * @param name Nome do jogador.
     * @param initialChances Número inicial de chances (vidas).
     */
    public Player(String name, int initialChances) {
        this.name = name;
        this.position = 0;
        this.points = 0;
        this.chances = initialChances; // total de chances (vidas)
    }

    // Getter para obter o nome do jogador
    public String getName() {
        return name;
    }

    // Getter para obter a posição atual no tabuleiro
    public int getPosition() {
        return position;
    }

    /**
     * Setter para definir diretamente a posição do jogador no tabuleiro.
     * É utilizado, por exemplo, quando o jogador precisa ser movido diretamente para o boss.
     * 
     * @param position Nova posição no tabuleiro.
     */
    public void setPosition(int position) { // Novo setter para ajustar a posição
        this.position = position;
    }

    /**
     * Move o jogador para frente no tabuleiro um certo número de casas.
     * Garante que não ultrapasse a casa final (100).
     * 
     * @param steps Quantidade de casas para avançar.
     */
    public void move(int steps) {
        position += steps;
        // Garante que o jogador não ultrapasse a posição final se rolar um dado muito alto
        if (position > 100) {
            position = 100;
        }
    }

    // Getter para obter a pontuação atual do jogador
    public int getPoints() {
        return points;
    }

    /**
     * Incrementa a pontuação do jogador.
     * Apenas valores positivos são considerados.
     * 
     * @param value Quantidade de pontos a serem adicionados.
     */
    public void gainPoints(int value) {
        if (value > 0) {
            points += value;
        }
    }

    /**
     * Reduz em uma unidade as chances (vidas) do jogador.
     * Garante que não fique negativo.
     */
    public void loseChance() {
        if (chances > 0) {
            chances--;
        }
    }

    // Getter para obter a quantidade de chances restantes
    public int getChances() {
        return chances;
    }

    /**
     * Verifica se o jogador ainda possui chances (vidas) para continuar jogando.
     * 
     * @return true se ainda possui chances, false se não tem mais.
     */
    public boolean hasChances() {
        return chances > 0;
    }
}
