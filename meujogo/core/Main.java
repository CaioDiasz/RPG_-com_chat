// Pacote onde a classe está localizada
package meujogo.core;

/**
 * Classe principal que inicia o jogo.
 */
public class Main {

    /**
     * Método main: ponto de entrada da aplicação.
     * Quando o programa é executado, ele começa por aqui.
     */
    public static void main(String[] args) {
        // Cria uma instância do controlador do jogo
        GameController game = new GameController();

        // Inicia o jogo chamando o método startGame()
        game.startGame();
    }
}
