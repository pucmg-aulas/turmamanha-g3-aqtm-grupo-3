package models;

import java.time.LocalDateTime;

public class Registro {
    private String idCliente; 
    private String placaVeiculo; 
    private LocalDateTime entrada; 
    private LocalDateTime saida; 
    private double valorPago; 

    public Registro(String idCliente, String placaVeiculo, LocalDateTime entrada) {
        this.idCliente = idCliente;
        this.placaVeiculo = placaVeiculo;
        this.entrada = entrada;
        this.saida = null; 
        this.valorPago = 0.0; 
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}
