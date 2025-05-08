public class Jogador {
    private String nome;
    private int vida;
    private int experiencia;
    private String areaAtual;

    public Jogador(String nome) {
        this.nome = nome;
        this.vida = 100; // Vida inicial
        this.experiencia = 0; // Experiência inicial
        this.areaAtual = "Início"; // Começa na área "Início"
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0; // Garantir que a vida não fique negativa
    }

    public void receberCura(int cura) {
        vida += cura;
        if (vida > 100) vida = 100; // A vida não pode ultrapassar 100
    }

    public void moverPara(String novaArea) {
        areaAtual = novaArea; // Muda a área do jogador
    }

    public void ganharExperiencia(int xp) {
        experiencia += xp; // Ganho de experiência
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public String getAreaAtual() {
        return areaAtual;
    }
}
