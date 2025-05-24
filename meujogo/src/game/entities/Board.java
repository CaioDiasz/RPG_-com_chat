package meujogo.src.game.entities;

import meujogo.src.game.entities.Question;
import meujogo.src.game.entities.Boss;
import meujogo.src.game.core.Zone;

public class Board {
    private Zone[] zones;

    public Board() {
        this.zones = createZones();
    }

    private Zone[] createZones() {
        return new Zone[] {
            new Zone(
                "Floresta de Gaia", 1, 16,
                new Question[] {
                    new Question("O que é o desmatamento?", new String[]{"Cortar árvores", "Plantar árvores", "Regar plantas"}, "Cortar árvores"),
                    new Question("Por que as florestas são importantes?", new String[]{"Para madeira", "Para gerar oxigênio", "Para construir cidades"}, "Para gerar oxigênio"),
                    new Question("O que é biodiversidade?", new String[]{"Variedade de seres vivos", "Quantidade de árvores", "Quantidade de espécies extintas"}, "Variedade de seres vivos")
                },
                new Boss("Rainha Dryad", new Question[]{
                    new Question("O que acontece quando as florestas desaparecem?", new String[]{"Mais oxigênio", "Menos biodiversidade", "Mais chuva"}, "Menos biodiversidade")
                })
            ),
            new Zone(
                "Caverna de Hades", 17, 33,
                new Question[] {
                    new Question("O que causa a poluição do ar?", new String[]{"Plantar árvores", "Queimar combustíveis fósseis", "Usar bicicletas"}, "Queimar combustíveis fósseis"),
                    new Question("Qual é um efeito da poluição?", new String[]{"Ar limpo", "Chuva ácida", "Solo fértil"}, "Chuva ácida"),
                    new Question("Como podemos reduzir a poluição?", new String[]{"Usando mais carros", "Usando menos energia", "Queimando mais lixo"}, "Usando menos energia")
                },
                new Boss("Espectro da Fumaça", new Question[]{
                    new Question("Qual é um efeito da poluição?", new String[]{"Ar limpo", "Chuva ácida", "Solo fértil"}, "Chuva ácida")
                })
            ),
            new Zone(
                "Rio de Estige", 34, 50,
                new Question[] {
                    new Question("Onde deve ir a água suja?", new String[]{"Nos rios", "Nas estações de tratamento", "No mar"}, "Nas estações de tratamento"),
                    new Question("O que é esgoto?", new String[]{"Água limpa", "Água suja", "Água de qualidade"}, "Água suja")
                },
                new Boss("Serpente das Inundações", new Question[]{
                    new Question("O que acontece com esgoto não tratado?", new String[]{"A água fica limpa", "A água fica suja", "Gera energia"}, "A água fica suja")
                })
            ),
            new Zone(
                "Céu de Zeus", 51, 67,
                new Question[] {
                    new Question("Qual energia é renovável?", new String[]{"Carvão", "Solar", "Petróleo"}, "Solar"),
                    new Question("O que é energia eólica?", new String[]{"Não renovável", "Limpa e renovável", "Perigosa"}, "Limpa e renovável"),
                    new Question("O que é uma energia limpa?", new String[]{"Energia solar", "Energia a carvão", "Energia nuclear"}, "Energia solar")
                },
                new Boss("Titã da Tempestade", new Question[]{
                    new Question("O que é energia solar?", new String[]{"Não renovável", "Limpa e renovável", "Perigosa"}, "Limpa e renovável")
                })
            ),
            new Zone(
                "Templo de Deméter", 68, 84,
                new Question[] {
                    new Question("Qual alimento é ecológico?", new String[]{"Fast food", "Comida orgânica local", "Lanches industrializados"}, "Comida orgânica local"),
                    new Question("O que é agricultura sustentável?", new String[]{"Usar agrotóxicos", "Usar rotação de culturas", "Queimar florestas"}, "Usar rotação de culturas")
                },
                new Boss("Golem da Colheita", new Question[]{
                    new Question("O que melhora a saúde do solo?", new String[]{"Pesticidas", "Compostagem", "Queima de lixo"}, "Compostagem")
                })
            ),
            new Zone(
                "Cidadela de Cronos", 85, 100,
                new Question[] {
                    new Question("O que é sustentabilidade?", new String[]{"Usar tudo rápido", "Equilibrar o presente e o futuro", "Evitar responsabilidade"}, "Equilibrar o presente e o futuro"),
                    new Question("Como podemos ajudar o meio ambiente?", new String[]{"Reciclando", "Desperdiçando", "Comprando mais coisas"}, "Reciclando")
                },
                new Boss("Cronos", new Question[]{
                    new Question("Qual é um hábito sustentável?", new String[]{"Desperdiçar água", "Reciclar", "Consumir demais"}, "Reciclar")
                })
            )
        };
    }

    public Zone getZoneByPosition(int position) {
        for (Zone zone : zones) {
            if (zone.isInZone(position)) {
                return zone;
            }
        }
        return null;
    }

    // Método para pegar uma pergunta específica para a casa atual
    public Question getQuestionForHouse(int position) {
        Zone currentZone = getZoneByPosition(position);
        if (currentZone != null) {
            int index = position - currentZone.getStartHouse();
            if (index < currentZone.getQuestions().length) {
                return currentZone.getQuestions()[index];
            }
        }
        return null;
    }
}
