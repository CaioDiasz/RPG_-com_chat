// Define o pacote onde esta classe está localizada
package meujogo.entities;

/**
 * Classe que representa um inimigo comum no jogo.
 * Esta classe pode ser utilizada para criar inimigos além dos chefes (Boss).
 * Atualmente, ela possui um nome e um nível de dificuldade.
 * 
 * OBS: Caso esta classe não esteja sendo utilizada em nenhuma parte do projeto,
 * considere removê-la para evitar código desnecessário.
 */
public class Enemy {

    // Nome do inimigo
    private String name;

    // Nível de dificuldade do inimigo (pode representar força, resistência, etc.)
    private int difficulty;

    /**
     * Construtor da classe Enemy.
     * Inicializa o inimigo com um nome e um nível de dificuldade.
     *
     * @param name       Nome do inimigo.
     * @param difficulty Nível de dificuldade do inimigo.
     */
    public Enemy(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    /**
     * Getter para obter o nome do inimigo.
     *
     * @return nome do inimigo.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter para obter o nível de dificuldade do inimigo.
     *
     * @return dificuldade do inimigo.
     */
    public int getDifficulty() {
        return difficulty;
    }
}
