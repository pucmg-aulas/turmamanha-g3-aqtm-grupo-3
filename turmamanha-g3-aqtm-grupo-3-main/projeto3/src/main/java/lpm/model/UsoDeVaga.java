package lpm.model;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;




public class UsoDeVaga {


    private static final double FRACAO_USO_MINUTOS = 15; // Tempo da fração em minutos
    private static final double VALOR_FRACAO = 4.0;      // Valor da fração
    private static final double VALOR_MAXIMO = 50.0;     // Valor máximo por vaga


    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;


    public LocalDateTime getEntrada() {
        return entrada;
    }


    public Vaga getVaga() {
        return vaga;
    }


    public LocalDateTime getSaida() {
        return saida;
    }


    public UsoDeVaga(Vaga vaga) {
        if (vaga != null && vaga.disponivel()) {
            vaga.estacionar();
            this.vaga = vaga;
            entrada = LocalDateTime.now();
        } else {
            throw new Error("Erro: vaga ocupada / inexistente");
        }
    }


    public UsoDeVaga(Vaga vaga, LocalDateTime entrada, LocalDateTime saida) {
        this.vaga = vaga;
        this.entrada = entrada;
        this.saida = saida;
    }


    public boolean sair() {
        saida = LocalDateTime.now();
        vaga.sair();
        return true;
    }


    public double valorPago() {
        // Se o ocupante ainda está estacionado, usamos o tempo atual para calcular o valor
        LocalDateTime horaSaida = (saida == null) ? LocalDateTime.now() : saida;


        // Calcular o tempo total em minutos
        long tempoTotalMinutos = ChronoUnit.MINUTES.between(entrada, horaSaida);


        // Calcular frações e valor inicial
        double fracoesUsadas = Math.ceil((double) tempoTotalMinutos / FRACAO_USO_MINUTOS); // Arredonda para cima
        double precoEstimado = VALOR_FRACAO * fracoesUsadas;


        // Aplicar limite máximo
        precoEstimado = Math.min(precoEstimado, VALOR_MAXIMO);


        // Ajustar preço com base no tipo de vaga
        precoEstimado = ajustarPrecoPorTipoDeVaga(precoEstimado, vaga.getId());


        return precoEstimado;
    }




    // Método auxiliar para ajustar o preço com base no tipo de ID da vaga
    private double ajustarPrecoPorTipoDeVaga(double preco, String idVaga) {
        if (idVaga == null || idVaga.isEmpty()) {
            System.out.println("ID da vaga inválido, sem ajuste aplicado.");
            return preco;
        }


        idVaga = idVaga.toUpperCase(); // Normaliza para evitar problemas de case sensitivity


        switch (idVaga.charAt(0)) {
            case 'R': // Sem ajuste, preço padrão
                return preco;
            case 'I': // 15% de desconto
                return preco * 0.85;
            case 'E': // 13% de desconto
                return preco * 0.87;
            case 'V': // 20% de acréscimo
                return preco * 1.20;
            default:
                System.out.println("Tipo de vaga desconhecido. Sem ajuste.");
                return preco;
        }
    }
}
