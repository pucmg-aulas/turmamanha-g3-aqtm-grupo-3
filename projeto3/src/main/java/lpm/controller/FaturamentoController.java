package lpm.controller;

import lpm.model.Estacionamento;
import lpm.view.Faturamento;

public class FaturamentoController {
    private final Faturamento view;
    private Estacionamento estacionamentoAtual;

    public FaturamentoController(Faturamento view, Estacionamento estacionamentoAtual) {
        this.view = view; this.estacionamentoAtual = estacionamentoAtual;
    }

    public void recuperarEstatisticas(String mes) {
        switch (mes) {
            case "Todos": view.getTextAreaEstatisticas().setText(getArrecadacaoTotal()); break;
            case "Janeiro": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(1) + "\n\n" + getTop5Mes(1)); break;
            case "Fevereiro": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(2) + "\n\n" + getTop5Mes(2)); break;
            case "Março": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(3) + "\n\n" + getTop5Mes(3)); break;
            case "Abril": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(4) + "\n\n" + getTop5Mes(4)); break;
            case "Maio": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(5) + "\n\n" + getTop5Mes(5)); break;
            case "Junho": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(6) + "\n\n" + getTop5Mes(6)); break;
            case "Julho": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(7) + "\n\n" + getTop5Mes(7)); break;
            case "Agosto": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(8) + "\n\n" + getTop5Mes(8)); break;
            case "Setembro": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(9) + "\n\n" + getTop5Mes(9)); break;
            case "Outubro": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(10) + "\n\n" + getTop5Mes(10)); break;
            case "Novembro": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(11) + "\n\n" + getTop5Mes(11)); break;
            case "Dezembro": view.getTextAreaEstatisticas().setText(getArrecadacaoMes(12) + "\n\n" + getTop5Mes(12)); break;
        }
    }

    private String getArrecadacaoMes(int mes) {
        return "Total arrecadado: " + estacionamentoAtual.arrecadacaoNoMes(mes);
    }

    private String getTop5Mes(int mes) {
        String top5 = estacionamentoAtual.top5Clientes(mes);
        return "Top 5 Clientes:" + top5;
    }

    private String getArrecadacaoTotal() {
        return "Total arrecadado: " + estacionamentoAtual.totalArrecadado();
    }
}