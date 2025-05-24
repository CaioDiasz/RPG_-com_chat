package meujogo.src.game.entities;

import meujogo.src.game.core.Zone;
import java.util.ArrayList;
import java.util.List;
import java.util.Random; // Embora não usado diretamente aqui, mantemos se for usado em Zone ou Question

public class Board {
    private List<Zone> zones;

    public Board() {
        this.zones = createZones();
    }

    private List<Zone> createZones() {
        List<Zone> zoneList = new ArrayList<>();

        // --- Primeiro Mundo: Montanha do Desperdício (Reciclagem) ---
        // Vilão: Golem de Sucata
        // Casas: 1 a 20
        Question[] montanhaDesperdicioQuestions = {
            new Question("Escolha a opção que descreve as cores corretas das latas de lixo recicláveis:", new String[]{"Azul – metal; Vermelha – vidro; Verde – papel; Amarela - plástico.", "Verde – papel; Azul – vidro; Vermelha – metal; Amarela - plástico.", "Azul – vidro; Verde - plástico; Amarela – papel; Vermelha – metal.", "Azul - plástico; Vermelha – papel; Verde – metal; Amarela – vidro.", "Azul – papel; Vermelha - plástico; Verde – vidro; Amarela – metal."}, "Azul – papel; Vermelha - plástico; Verde – vidro; Amarela – metal."),
            new Question("Qual é o jeito certo de reciclar um lixo?", new String[]{"Jogando tudo no lixo verde, já que ele é reciclável.", "Separando os resíduos por tipo, limpando e colocando nas lixeiras corretas.", "Lavar o lixo com detergente e jogar na pia.", "Queimar todo o lixo em casa para reduzir o volume.", "Enterrar o lixo no quintal para virar adubo."}, "Separando os resíduos por tipo, limpando e colocando nas lixeiras corretas."),
            new Question("Para onde vão os lixos que não são descartados corretamente?", new String[]{"São levados automaticamente para a reciclagem por caminhões.", "Para rios, mares ou ficam espalhados no meio ambiente, causando poluição.", "São reaproveitados por animais para construção de abrigos.", "São usados pelas prefeituras como decoração urbana.", "Evaporam com o tempo e não causam impacto ambiental."}, "Para rios, mares ou ficam espalhados no meio ambiente, causando poluição.")
        };
        Question[] golemSucataBossQuestions = {
            new Question("A coleta seletiva é um processo importante na gestão de resíduos sólidos. Quais são os principais objetivos da coleta seletiva?", new String[]{"Aumentar o volume de lixo enviado para aterros sanitários.", "Reduzir a poluição do ar e da água, economizar recursos naturais e diminuir a quantidade de lixo em aterros sanitários.", "Incentivar a produção de mais lixo.", "Apenas separar o lixo por cor sem benefício ambiental real.", "Transferir o lixo para outros países."}, "Reduzir a poluição do ar e da água, economizar recursos naturais e diminuir a quantidade de lixo em aterros sanitários.")
        };
        zoneList.add(new Zone("Montanha do Desperdício", 1, 20, montanhaDesperdicioQuestions, new Boss("Golem de Sucata", golemSucataBossQuestions)));

        // --- Segundo Mundo: Terras Obscuras (Fontes de energia Limpa/Sustentável) ---
        // Vilão: Demônio Nuclear
        // Casas: 21 a 40
        Question[] terrasObscurasQuestions = {
            new Question("Em qual cidade foi causada o maior acidente nuclear da história?", new String[]{"Hiroshima.", "Nagasaki.", "Chernobyl.", "Fukushima.", "Three Mile Island."}, "Chernobyl."),
            new Question("Em qual das opções abaixo, a energia nuclear é mais usada?", new String[]{"Geração de energia elétrica e propulsão de submarinos.", "Cozinhar alimentos e aquecer casas.", "Construção de carros e fabricação de roupas.", "Produção de brinquedos e jogos.", "Limpeza de rios e lagos."}, "Geração de energia elétrica e propulsão de submarinos."),
            new Question("A energia eólica é uma das fontes de energia renovável que mais cresce no mundo. Qual é a principal vantagem da energia eólica?", new String[]{"Não causa impacto ambiental, pois não emite gases de efeito estufa durante a operação.", "É a energia mais barata para ser produzida.", "Pode ser usada em qualquer lugar do mundo.", "Não ocupa espaço na paisagem.", "É a mais fácil de armazenar."}, "Não causa impacto ambiental, pois não emite gases de efeito estufa durante a operação.")
        };
        Question[] demonioNuclearBossQuestions = {
            new Question("Qual é a relação entre as energias renováveis e o desenvolvimento sustentável?", new String[]{"As energias renováveis são mais caras e não contribuem para o desenvolvimento sustentável.", "As energias renováveis diminuem a dependência de combustíveis fósseis e reduzem as emissões de gases de efeito estufa, promovendo um desenvolvimento mais equilibrado.", "Não há relação, pois o desenvolvimento sustentável foca apenas na economia.", "As energias renováveis aumentam a poluição do ar.", "As energias renováveis são apenas uma moda passageira sem impacto real."}, "As energias renováveis diminuem a dependência de combustíveis fósseis e reduzem as emissões de gases de efeito estufa, promovendo um desenvolvimento mais equilibrado.")
        };
        zoneList.add(new Zone("Terras Obscuras", 21, 40, terrasObscurasQuestions, new Boss("Demônio Nuclear", demonioNuclearBossQuestions)));

        // --- Terceiro Mundo: Deserto da Escassez (Uso Consciente da Água) ---
        // Vilão: Kraken da Seca
        // Casas: 41 a 60
        Question[] desertoEscassezQuestions = {
            new Question("Qual é a principal fonte de água doce no planeta?", new String[]{"Oceanos.", "Lençóis freáticos e aquíferos.", "Geleiras e calotas polares.", "Rios e lagos.", "Chuva."}, "Geleiras e calotas polares."),
            new Question("O que é pegada hídrica?", new String[]{"A quantidade de água que uma pessoa bebe por dia.", "A quantidade de água necessária para produzir bens e serviços.", "A quantidade de água em um deserto.", "A quantidade de água desperdiçada em casa.", "A quantidade de água que os rios carregam."}, "A quantidade de água necessária para produzir bens e serviços."),
            new Question("Qual é a principal causa da poluição da água?", new String[]{"Despejo de esgoto doméstrico e industrial sem tratamento.", "Chuva ácida.", "Pesca excessiva.", "Construção de barragens.", "Uso de energia solar."}, "Despejo de esgoto doméstrico e industrial sem tratamento.")
        };
        Question[] krakenSecaBossQuestions = {
            new Question("Como a escassez de água pode afetar a produção de alimentos?", new String[]{"Aumentando a produtividade agrícola devido à falta de chuva.", "Prejudicando as colheitas e a pecuária, levando à insegurança alimentar.", "Não afeta a produção de alimentos, pois a água é abundante.", "Melhorando a qualidade dos alimentos.", "Tornando os alimentos mais baratos."}, "Prejudicando as colheitas e a pecuária, levando à insegurança alimentar.")
        };
        zoneList.add(new Zone("Deserto da Escassez", 41, 60, desertoEscassezQuestions, new Boss("Kraken da Seca", krakenSecaBossQuestions)));

        // --- Quarto Mundo: Floresta Incendiada (Desmatamento e Queimadas) ---
        // Vilão: Incendiários
        // Casas: 61 a 80
        Question[] florestaIncendiadaQuestions = {
            new Question("Quais são as principais causas do desmatamento na Amazônia?", new String[]{"Crescimento urbano e industrialização descontrolada.", "Expansão da agricultura e pecuária, exploração madeireira ilegal e grilagem de terras.", "Aumento da população de animais silvestres.", "Desastres naturais como terremotos.", "Chuva ácida."}, "Expansão da agricultura e pecuária, exploração madeireira ilegal e grilagem de terras."),
            new Question("Como o desmatamento pode afetar o clima global?", new String[]{"Aumentando a produção de oxigênio e diminuindo a temperatura.", "Reduzindo a biodiversidade e liberando gases de efeito estufa, contribuindo para o aquecimento global.", "Intensificando as chuvas na floresta.", "Facilitando a infiltração da água no solo.", "Tornando os rios mais limpos."}, "Reduzindo a biodiversidade e liberando gases de efeito estufa, contribuindo para o aquecimento global."),
            // Nova pergunta para o Quarto Mundo: Amazônia Ardente
            new Question("Qual é o impacto global do desmatamento e das queimadas na Amazônia em relação às mudanças climáticas?", new String[]{"Aumenta a produção de oxigênio, equilibrando os níveis de CO₂ na atmosfera.", "Reduz a biodiversidade local, mas não afeta o clima global.", "Libera grandes quantidades de CO₂, reduz a capacidade de absorção de carbono alternado o clima em escala planetária.", "Piora a qualidade do solo, favorecendo a agricultura em outras regiões.", "Não há evidências científicas que liguem a Amazônia ao clima global."}, "Libera grandes quantidades de CO₂, reduz a capacidade de absorção de carbono alternado o clima em escala planetária.")
        };
        Question[] incendiariosBossQuestions = {
            new Question("Como o desmatamento afeta o regime hídrico e as chuvas na região amazônica?", new String[]{"Intensificam as chuvas na floresta.", "Reduzem a evapotranspiração, diminuem a formação de chuvas e afetam o regime hídrico regional.", "Facilitam a infiltração da água no solo, fortalecendo os lençóis freáticos.", "Tornam os rios mais limpos ao evaporar impurezas com o calor.", "Não há impacto significativo."}, "Reduzem a evapotranspiração, diminuem a formação de chuvas e afetam o regime hídrico regional.")
        };
        zoneList.add(new Zone("Floresta Incendiada", 61, 80, florestaIncendiadaQuestions, new Boss("Incendiários", incendiariosBossQuestions)));

        // --- Quinto Mundo: Santuário em Ruínas das Capivaras (Preservação da Biodiversidade) ---
        // Vilão: Caçadores Ilegais
        // Casas: 81 a 100 (Final)
        Question[] santuarioCapivarasQuestions = {
            new Question("Quais são os principais animais vítimas de caçadores ilegais?", new String[]{"Veado-catingueiro, boi, papagaio-do-mato e galinha-d’água, por sua capacidade de adaptação.", "Capivara, vaca, pombo e cachorro-do-mato, devido ao controle de pragas urbanas.", "Tamanduá-bandeira, peixe-boi, minhoca e coruja, usados como animais domésticos.", "Jacaré, jiboia, cavalo-marinho e siri, por sua toxina venenosa.", "Onça-pintada, tatu-bola, jacaré e arara-azul, por causa de sua pele, carne ou valor no mercado ilegal."}, "Onça-pintada, tatu-bola, jacaré e arara-azul, por causa de sua pele, carne ou valor no mercado ilegal."),
            new Question("Como o tráfico de animais silvestres afeta o equilíbrio ecológico dos biomas brasileiros?", new String[]{"Gera alterações locais, mas contribui para manter o controle populacional de algumas espécies.", "Pode interferir nos processos naturais de reprodução e dispersão, afetando cadeias ecológicas de forma silenciosa, mas significativa.", "Não causa efeitos diretos significativos, desde que os animais não sejam retirados em grande número.", "Pode ser compensado pela reprodução em cativeiro.", "Apenas afeta espécies de pequeno porte."}, "Pode interferir nos processos naturais de reprodução e dispersão, afetando cadeias ecológicas de forma silenciosa, mas significativa."),
            // Nova pergunta para o Quinto Mundo: Santuário em Ruínas das Capivaras
            new Question("Como a fragmentação de habitats naturais prejudica espécies ameaçadas, como a onça-pintada e a arara-azul?", new String[]{"Porque reduz a disponibilidade de alimentos industrializados para os animais.", "Porque limita o espaço para turistas observarem os animais em cativeiro.", "Porque os animais preferem áreas verdes urbanas para se adaptarem mais rápido.", "Porque isola populações, dificulta a reprodução, aumenta a competição por recursos e eleva o risco de extinção.", "Porque facilita o controle de doenças entre as espécies nativas."}, "Porque isola populações, dificulta a reprodução, aumenta a competição por recursos e eleva o risco de extinção.")
        };
        Question[] cacadoresIlegaisBossQuestions = {
            new Question("Quais são as consequências da perda de biodiversidade para o meio ambiente e para a humanidade?", new String[]{"Não há consequências, pois a natureza se adapta rapidamente.", "Aumenta a resistência dos ecossistemas a mudanças climáticas.", "Compromete serviços ecossistêmicos essenciais, como polinização, purificação da água e controle de pragas, afetando a saúde humana e a economia.", "Gera novas espécies mais resistentes.", "Melhora a qualidade do solo."}, "Compromete serviços ecossistêmicos essenciais, como polinização, purificação da água e controle de pragas, afetando a saúde humana e a economia.")
        };
        zoneList.add(new Zone("Santuário em Ruínas das Capivaras", 81, 100, santuarioCapivarasQuestions, new Boss("Caçadores Ilegais", cacadoresIlegaisBossQuestions)));

        return zoneList;
    }

    public Zone getZoneByPosition(int position) {
        for (Zone zone : zones) {
            if (zone.isInZone(position)) {
                return zone;
            }
        }
        return null;
    }
}