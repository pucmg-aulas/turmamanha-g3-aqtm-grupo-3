package lpm.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UsoDeVaga {

    private static final double FRACAO_USO_MINUTOS = 15;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

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
        long tempoTotalMinutos;
        double precoEstimado;

        if (saida != null) {
            tempoTotalMinutos = ChronoUnit.MINUTES.between(entrada, saida);
            double fracoesUsadas = tempoTotalMinutos / FRACAO_USO_MINUTOS;
            precoEstimado = VALOR_FRACAO * fracoesUsadas;

            // Aplicação do limite máximo
            if (precoEstimado > VALOR_MAXIMO) {
                precoEstimado = VALOR_MAXIMO;
            }

            // Ajuste de preço baseado no ID da vaga
            String idVaga = vaga.getId();
            if (idVaga.startsWith("I")) {
                precoEstimado *= 0.85; // 15% de desconto
            } else if (idVaga.startsWith("E")) {
                precoEstimado *= 0.87; // 13% de desconto
            } else if (idVaga.startsWith("V")) {
                precoEstimado *= 1.20; // 20% de acréscimo
            }

            return precoEstimado;
        } else {
            throw new Error("Erro: o ocupante ainda nao saiu da vaga.");
        }
    }
}