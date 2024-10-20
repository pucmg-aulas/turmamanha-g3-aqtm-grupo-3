package models;

public class VagaPCD extends Vaga {

    public VagaPCD(String id) {
        super(id);
    }

    @Override
    public double calcularPreco(int minutos) {
        // Cálculo: R$3,00 por cada 30 minutos com 13% de desconto
        int blocosDe30Minutos = (int) Math.ceil(minutos / 30.0);
        double precoBase = blocosDe30Minutos * 3;
        return precoBase - (precoBase * 0.13); // Subtrai 13%
    }
}
